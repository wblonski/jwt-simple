package pl.wblo.jwt.restobjects;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class ErrorResponseObj implements Serializable {
    private Date timestamp;
    private int status;
    private String error;
    private String path;
    
    public ErrorResponseObj(Date timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }
    
    public ErrorResponseObj() {
    }
    
    public static ErrorResponseObjBuilder builder() {
        return new ErrorResponseObjBuilder();
    }
    
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public String getError() {
        return this.error;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ErrorResponseObj)) return false;
        final ErrorResponseObj other = (ErrorResponseObj) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$timestamp = this.getTimestamp();
        final Object other$timestamp = other.getTimestamp();
        if (this$timestamp == null ? other$timestamp != null : !this$timestamp.equals(other$timestamp)) return false;
        if (this.getStatus() != other.getStatus()) return false;
        final Object this$error = this.getError();
        final Object other$error = other.getError();
        if (this$error == null ? other$error != null : !this$error.equals(other$error)) return false;
        final Object this$path = this.getPath();
        final Object other$path = other.getPath();
        if (this$path == null ? other$path != null : !this$path.equals(other$path)) return false;
        return true;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ErrorResponseObj;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $timestamp = this.getTimestamp();
        result = result * PRIME + ($timestamp == null ? 43 : $timestamp.hashCode());
        result = result * PRIME + this.getStatus();
        final Object $error = this.getError();
        result = result * PRIME + ($error == null ? 43 : $error.hashCode());
        final Object $path = this.getPath();
        result = result * PRIME + ($path == null ? 43 : $path.hashCode());
        return result;
    }
    
    public String toString() {
        return "ErrorResponseObj(timestamp=" + this.getTimestamp() + ", status=" + this.getStatus() + ", error=" + this.getError() + ", path=" + this.getPath() + ")";
    }
    
    public static class ErrorResponseObjBuilder {
        private Date timestamp;
        private int status;
        private String error;
        private String path;
        
        ErrorResponseObjBuilder() {
        }
        
        public ErrorResponseObjBuilder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        
        public ErrorResponseObjBuilder status(int status) {
            this.status = status;
            return this;
        }
        
        public ErrorResponseObjBuilder error(String error) {
            this.error = error;
            return this;
        }
        
        public ErrorResponseObjBuilder path(String path) {
            this.path = path;
            return this;
        }
        
        public ErrorResponseObj build() {
            return new ErrorResponseObj(this.timestamp, this.status, this.error, this.path);
        }
        
        public String toString() {
            return "ErrorResponseObj.ErrorResponseObjBuilder(timestamp=" + this.timestamp + ", status=" + this.status + ", error=" + this.error + ", path=" + this.path + ")";
        }
    }
    
    //        String ErrorResponseBody = """
    //                {
    //                  "timestamp": "2025-09-05T17:58:27.477+00:00",
    //                  "status": 500,
    //                  "error": "Internal Server Error",
    //                  "path": "/api/v1/auth/register"
    //                } """;
}
