package cc.txin.admin.service.impl;

import cc.txin.admin.entity.Download;
import cc.txin.admin.mapper.DownloadMapper;
import cc.txin.admin.service.IDownloadService;
import cc.txin.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("downloadService")
public class DownloadServiceImpl extends BaseServiceImpl<Download> implements IDownloadService {
    @Autowired
    DownloadMapper downloadMapper;

}
