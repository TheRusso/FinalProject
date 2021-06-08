package com.example.demo.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TagHandler extends SimpleTagSupport {
    private String lang;

    public void setLang(String lang) {
        this.lang = lang;
    }

    StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        SimpleDateFormat dateFormat;

        if(lang != null){
            if(lang.equals("en")){
                dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            }else {
                dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            }
            getJspContext().getOut().print(dateFormat.format(new Date()));
        }
    }
}
