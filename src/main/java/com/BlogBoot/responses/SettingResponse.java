package com.BlogBoot.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SettingResponse {

    boolean MULTIUSER_MODE;
    boolean POST_PREMODERATION;
    boolean STATISTICS_IS_PUBLIC;

}
