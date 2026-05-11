package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.PageParam;
import lombok.Data;

@Data
public class UploadFileQuery extends PageParam {

    private  String groupId;

    private String fileSourceId;

}
