package com.keke.cloud.web.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.keke.cloud.common.domain.RestResult;
import com.keke.cloud.common.util.DateUtil;
import com.keke.cloud.common.util.PathUtil;
import com.keke.cloud.web.domain.RecoveryFile;
import com.keke.cloud.web.domain.UserBean;
import com.keke.cloud.web.domain.UserFile;
import com.keke.cloud.web.dto.BatchDeleteRecoveryFileDTO;
import com.keke.cloud.web.dto.DeleteRecoveryFileDTO;
import com.keke.cloud.web.dto.RestoreFileDTO;
import com.keke.cloud.web.service.IRecoveryFileService;
import com.keke.cloud.web.service.IUserFileService;
import com.keke.cloud.web.service.IUserService;
import com.keke.cloud.web.vo.RecoveryFileListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*回收站文件控制层类*/
@Api(value = "recoveryfile", tags= "文件删除后会进入回收站，该接口主要实现对回收站文件进行恢复、彻底删除等管理")
@RestController
@Slf4j
@RequestMapping("/recoveryfile")
public class RecoveryFileController {
    @Resource
    IRecoveryFileService recoveryFileService;
    @Resource
    IUserFileService userFileService;
    @Resource
    IUserService userService;

    @ApiOperation(value = "删除回收站的文件", notes = "该接口主要实现了彻底删除回收文件", tags = {"recoveryfile"})
    @RequestMapping(value = "/deleterecoveryfile", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<String> deleteRecoveryFile(@RequestBody DeleteRecoveryFileDTO deleteRecoveryFileDTO, @RequestHeader("token") String token) {
        RestResult<String> restResult = new RestResult<String>();
        RecoveryFile recoveryFile = recoveryFileService.getById(deleteRecoveryFileDTO.getRecoveryFileId());
        UserFile userFile =userFileService.getById(recoveryFile.getUserFileId());
        recoveryFileService.deleteRecoveryFile(userFile);
        recoveryFileService.removeById(deleteRecoveryFileDTO.getRecoveryFileId());
        restResult.setSuccess(true);
        restResult.setData("删除成功");
        return restResult;
    }

    @ApiOperation(value = "展现回收站文件列表", notes = "该接口将用户回收站的文件以列表形式展示", tags = {"recoveryfile"})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<List<RecoveryFileListVo>> getRecoveryFileList(@RequestHeader("token") String token) {
        RestResult<List<RecoveryFileListVo>> restResult = new RestResult<List<RecoveryFileListVo>>();
        UserBean sessionUserBean = userService.getUserBeanByToken(token);
        List<RecoveryFileListVo> recoveryFileList = recoveryFileService.selectRecoveryFileList(sessionUserBean.getUserId());
        restResult.setData(recoveryFileList);
        restResult.setSuccess(true);
        return restResult;
    }
    @ApiOperation(value = "恢复还原已删除的文件", notes = "该接口主要用来恢复还原已删除的文件", tags = {"recoveryfile"})
    @RequestMapping(value = "/restorefile", method = RequestMethod.POST)
    @ResponseBody
    public RestResult restoreFile(@RequestBody RestoreFileDTO restoreFileDto, @RequestHeader("token") String token) {
        RestResult<String> restResult = new RestResult<String>();
        UserBean sessionUserBean = userService.getUserBeanByToken(token);
        LambdaUpdateWrapper<UserFile> userFileLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userFileLambdaUpdateWrapper.set(UserFile::getDeleteFlag, 0)
                .set(UserFile::getDeleteBatchNum, "")
                .eq(UserFile::getDeleteBatchNum, restoreFileDto.getDeleteBatchNum());
        userFileService.update(userFileLambdaUpdateWrapper);

        String filePath = PathUtil.getParentPath(restoreFileDto.getFilePath());
        while(filePath.indexOf("/") != -1) {
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            filePath = PathUtil.getParentPath(filePath);
            LambdaQueryWrapper<UserFile> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserFile::getFilePath, filePath + "/")
                    .eq(UserFile::getDeleteFlag, 0)
                    .eq(UserFile::getUserId, sessionUserBean.getUserId());
            List<UserFile> userFileList = userFileService.list(lambdaQueryWrapper);
            if (userFileList.size() == 0) {
                UserFile userFile = new UserFile();
                userFile.setUserId(sessionUserBean.getUserId());
                userFile.setFileName(fileName);
                userFile.setFilePath(filePath + "/");
                userFile.setDeleteFlag(0);
                userFile.setIsDir(1);
                userFile.setUploadTime(DateUtil.getCurrentTime());

                userFileService.save(userFile);
            }
        }
        LambdaQueryWrapper<RecoveryFile> recoveryFileServiceLambdaQueryWrapper = new LambdaQueryWrapper<>();
        recoveryFileServiceLambdaQueryWrapper.eq(RecoveryFile::getDeleteBatchNum, restoreFileDto.getDeleteBatchNum());
        recoveryFileService.remove(recoveryFileServiceLambdaQueryWrapper);
        restResult.setSuccess(true);
        restResult.setData("恢复成功");
        return restResult;
    }

    @ApiOperation(value = "进行批量删除回收站文件", notes = "该接口主要实现批量删除回收站文件", tags = {"recoveryfile"})
    @RequestMapping(value = "/batchdelete", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<String> batchDeleteRecoveryFile(@RequestBody BatchDeleteRecoveryFileDTO batchDeleteRecoveryFileDTO, @RequestHeader("token") String token) {
        RestResult<String> restResult = new RestResult<String>();


        List<RecoveryFile> recoveryFileList = JSON.parseArray(batchDeleteRecoveryFileDTO.getRecoveryFileIds(), RecoveryFile.class);
        for (RecoveryFile recoveryFile : recoveryFileList) {

            RecoveryFile recoveryFile1 = recoveryFileService.getById(recoveryFile.getRecoveryFileId());
            UserFile userFile =userFileService.getById(recoveryFile1.getUserFileId());

            recoveryFileService.deleteRecoveryFile(userFile);
            recoveryFileService.removeById(recoveryFile.getRecoveryFileId());
        }
        restResult.setSuccess(true);
        restResult.setData("批量删除成功");
        return restResult;
    }
}
