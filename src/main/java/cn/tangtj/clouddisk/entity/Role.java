package cn.tangtj.clouddisk.entity;

public class Role {
    private Integer userId;
    private Integer fileMaxCount = 50;
    private Long fileSizeMaxTotol = 50 * 1024 * 1024L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFileMaxCount() {
        return fileMaxCount;
    }

    public void setFileMaxCount(Integer fileMaxCount) {
        this.fileMaxCount = fileMaxCount;
    }

    public Long getFileSizeMaxTotol() {
        return fileSizeMaxTotol;
    }

    public void setFileSizeMaxTotol(Long fileSizeMaxTotol) {
        this.fileSizeMaxTotol = fileSizeMaxTotol;
    }
}
