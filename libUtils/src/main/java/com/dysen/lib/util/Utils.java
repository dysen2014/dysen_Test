package com.dysen.lib.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/1/13.
 */

public class Utils {

    static ArrayList<SampleModel> sampleData;

    public static ArrayList<SampleModel> getSampleData(int size){

        sampleData = new ArrayList<SampleModel>(size);
        for (int i = 0; i < size; i++) {
            //
            System.out.println(new SampleModel("新的列表项"+i).getSampleText());
            sampleData.add(new SampleModel("新的列表项"+i));
        }
        return sampleData;
    }

    public static class SampleModel {

        private String sampleText;

        public SampleModel(String sampleText) {
            this.sampleText = sampleText;
        }

        public String getSampleText() {
            return sampleText;
        }

        public void setSampleText(String sampleText) {
            this.sampleText = sampleText;
        }
    }
}
