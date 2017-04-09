package cn.pandy.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 项目: Crawling
 * 作者: 潘清剑(qingjian.pan@tongdun.cn)
 * 时间: 2017/3/24 上午10:47
 * 描述:
 */
public class Utils {

    /**
     * 时间格式转换
     *
     * @param time
     * @return
     */
    public static String timeTrans(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String md5(String value) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
            digest.update(value.getBytes());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertSendTime(String sendTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        int yearNow = now.get(Calendar.YEAR);
        if(sendTime.matches("\\d{2}-\\d{2}")){
            String[] split = sendTime.split("-");
            Calendar before = Calendar.getInstance();
            before.set(yearNow,Integer.valueOf(split[0])-1,Integer.valueOf(split[1]));
            if(before.after(now)){
                before.set(yearNow-1,Integer.valueOf(split[0])-1,Integer.valueOf(split[1]));
            }
            sendTime = format.format(before.getTime());
        }else{
            sendTime = format.format(now.getTime());
        }
        return sendTime;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(convertSendTime("12-03"));
        System.out.println(convertSendTime("22分钟"));
        System.out.println(convertSendTime("01-20"));
    }


}
