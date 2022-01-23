package csdn.c4.dao;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具类
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/1/22 9:15
 */
@Slf4j
public final class DbUtil {

    public static final Db DB;

    static {
        DB = Db.use();
        InputStream is = DbUtil.class.getClassLoader().getResourceAsStream("init.sql");
        if (is != null) {
            String initSql = IoUtil.readUtf8(is);
            try {
                DB.execute(initSql);
                initData();
            } catch (SQLException t) {
                t.printStackTrace();
            }
        }
    }

    public static Db getDb() {
        return DB;
    }

    public static void initData() throws SQLException {
        File zipFile = HttpUtil.downloadFileFromUrl("https://csdn-task.oss-cn-hangzhou.aliyuncs.com/demo/studyNginx.zip", "temp.zip");
        File unZipFile = ZipUtil.unzip(zipFile);
        log.info("下载文件路径: {}", zipFile.getAbsolutePath());
        File studyNginxDir = new File(unZipFile, "studyNginx");
        System.setProperty("parent_file", studyNginxDir.getAbsolutePath());
        File[] studyNginxFiles = studyNginxDir.listFiles();
        assert studyNginxFiles != null;
        List<Entity> entityList = new ArrayList<>();
        for (File studyNginxFile : studyNginxFiles) {
            Entity fileEncryption = Entity.create("file_encryption");
            fileEncryption.set("file_name", studyNginxFile.getName());
            String utf8String = FileUtil.readUtf8String(studyNginxFile);
            //随机生成密钥
            String key = RandomUtil.randomString(32);
            String iv = RandomUtil.randomString(16);
            fileEncryption.set("key", key);
            fileEncryption.set("iv", iv);
            AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
            String encryptHex = aes.encryptHex(utf8String);
            FileUtil.writeUtf8String(encryptHex, studyNginxFile);
            log.info("已加密文件: {}", studyNginxFile.getAbsolutePath());
            entityList.add(fileEncryption);
        }
        getDb().tx(db -> {
            db.execute("DELETE FROM file_encryption;");
            db.insert(entityList);
        });
    }
}
