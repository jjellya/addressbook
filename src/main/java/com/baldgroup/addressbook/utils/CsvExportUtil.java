package com.baldgroup.addressbook.utils;

import com.sun.deploy.net.HttpResponse;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Create By  @林俊杰
 * 2020/5/6 21:04
 *
 * @version 1.0
 */
public class CsvExportUtil {

    /*CSV文件行分隔符*/
    private static final String CSV_ROW_SEPARATOR = System.lineSeparator();

    /*CSV文件列分隔符*/
    private static final String CSV_COL_SEPARATOR = ",";


    /**
     * @param dataList 集合数据
     * @param titles   表头部数据
     * @param keys     表内容的键值
     * @param os       输出流
     */
    public static void doExport(List<Map<String, Object>> dataList, String titles, String keys, OutputStream os) throws Exception{

        //用StringBuffer防止出岔子;
        StringBuffer buffer = new StringBuffer();

        String[] titleArray = null;
        String[] keyArray = null;

        titleArray = titles.split(",");
        keyArray = keys.split(",");

        //放置表头;
        for (String title:titleArray
             ) {
            buffer.append(title).append(CSV_COL_SEPARATOR);
        }
        buffer.append(CSV_ROW_SEPARATOR);

        //放置数据;
        if(!CollectionUtils.isEmpty(dataList))//留意此处的CollectionUtils的导入包
        for (Map<String, Object> data:dataList
             ) {
            for (String key:keyArray
                 ) {
                buffer.append(data.get(key)).append(CSV_COL_SEPARATOR);
            }
            buffer.append(CSV_ROW_SEPARATOR);
        }

        /**
         * Attention: This is coded by "UTF-8",and you can try "GBK";
         */
        os.write(buffer.toString().getBytes("UTF-8"));
        os.flush();
        
    }


    public static void responseSetProperties(HttpServletResponse response,String fileName) throws UnsupportedEncodingException {

        String fName = fileName + ".csv";

        String utf = "UTF-8";

        //设置响应;
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma","public");
        response.setHeader("Cache-Control","max-age=30");
        response.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(fName,utf));

    }
}
