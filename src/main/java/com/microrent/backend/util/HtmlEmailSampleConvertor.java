package com.microrent.backend.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Scanner;

@Component
public class HtmlEmailSampleConvertor {

    private final String htmlEmailRegistrationDataSample;

    public HtmlEmailSampleConvertor() {
        this.htmlEmailRegistrationDataSample = readFileAndConvertToString("htmlSamples/EmailRegistrationDataSample.html");
    }

    public String GetHtmlEmailRegistrationData(String email, String password){
        return htmlEmailRegistrationDataSample.replace("client_email", email).replace("client_password", password);
    }

    private String readFileAndConvertToString (String fileName) {
        try {
            Scanner sc = new Scanner(getFileFromResourceAsStream(fileName));
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()){
                sb.append(sc.nextLine());
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream getFileFromResourceAsStream(String fileName) throws IOException {
        return new ClassPathResource(fileName).getInputStream();
    }

}
