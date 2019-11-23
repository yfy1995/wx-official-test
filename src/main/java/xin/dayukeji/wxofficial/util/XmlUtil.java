package xin.dayukeji.wxofficial.util;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:40
 * @Version 1.0
 * @description 封装和处理xml文件
 */
public class XmlUtil {

    private static final String PREFIX_XML = "<xml>";

    private static final String SUFFIX_XML = "</xml>";

    private static final String PREFIX_CDATA = "<![CDATA[";

    private static final String SUFFIX_CDATA = "]]>";

    /**
     * 转化成xml, 单层无嵌套
     *
     * @param param
     * @param isAddCDATA ture 加CDATA标签  false 不加CDATA标签
     * @return
     */
    public static String xmlFormat(Map<String, String> param, boolean isAddCDATA) {

        StringBuffer strBuff = new StringBuffer(PREFIX_XML);
        if (CollectionUtil.isNotEmpty(param)) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                strBuff.append("<").append(entry.getKey()).append(">");
                if (isAddCDATA && !"CreateTime".equals(entry.getKey())) {
                    strBuff.append(PREFIX_CDATA);
                    if (StringUtil.isNotEmpty(entry.getValue())) {
                        strBuff.append(entry.getValue());
                    }
                    strBuff.append(SUFFIX_CDATA);
                } else {
                    if (StringUtil.isNotEmpty(entry.getValue())) {
                        strBuff.append(entry.getValue());
                    }
                }
                strBuff.append("</").append(entry.getKey()).append(">");
            }
        }
        return strBuff.append(SUFFIX_XML).toString();
    }

    /**
     * 解析xml
     *
     * @param xml
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static Map<String, String> xmlParse(String xml) throws XmlPullParserException, IOException {
        Map<String, String> map = null;
        if (StringUtil.isNotEmpty(xml)) {
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
            XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
            // 为xml设置要解析的xml数据
            pullParser.setInput(inputStream, "UTF-8");
            int eventType = pullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        map = new HashMap<>();
                        break;
                    case XmlPullParser.START_TAG:
                        String key = pullParser.getName();
                        if ("xml".equals(key)) {
                            break;
                        }
                        String value = pullParser.nextText().trim();
                        map.put(key, value);
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                }
                eventType = pullParser.next();
            }
        }
        return map;
    }
}
