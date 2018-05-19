package cn.tangtj.clouddisk.dao;

import cn.tangtj.clouddisk.entity.UploadFile;
import cn.tangtj.clouddisk.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tang
 */
@Repository
public class FileDao {

    public List<UploadFile> findByUserId(int id) {
        final String sql = "SELECT * FROM file WHERE user_Id = ?";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            return runner.query(sql, new BeanListHandler<>(UploadFile.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UploadFile findById(int id) {
        final String sql = "SELECT * FROM file WHERE fileId = ? And userId = ?";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            return runner.query(sql, new BeanHandler<>(UploadFile.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UploadFile findByShareCode(String code){
        String sql = "select * from file where shareCode = ?";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            return runner.query(sql,new BeanHandler<>(UploadFile.class), code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UploadFile save(UploadFile file){
        String sql = "insert into file(userId,fileName,fileSize,uploadDate,sharing) value(?,?,?,?,?)";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            return runner.insert(sql, new  BeanHandler<>(UploadFile.class),
            file.getUserId(), file.getFileName(), file.getFileSize(), file.getUploadDate(), file.getSharing());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
