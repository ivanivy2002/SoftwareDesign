package org.example.command.history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OutputLog implements java.io.Serializable {
    protected List<String> outputLog = new ArrayList<>();
    protected String sessionHeader;
    //    protected int sessionHeaderNum ;
    protected String sessionEnder;

    //    protected LocalDateTime duration;
    protected String duration;
    protected String fileName;
}
