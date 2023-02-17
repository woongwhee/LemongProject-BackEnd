package site.lemongproject.common.type.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.time.LocalDateTime;

public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {
        public LocalDateTimeTypeHandler() {
        }

        public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
            ps.setTimestamp(i, Timestamp.valueOf(parameter));
        }

        public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
            Timestamp timestamp = rs.getTimestamp(columnName);
            return getLocalDateTime(timestamp);
        }

        public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
            Timestamp timestamp = rs.getTimestamp(columnIndex);
            return getLocalDateTime(timestamp);
        }

        public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
            Timestamp timestamp = cs.getTimestamp(columnIndex);
            return getLocalDateTime(timestamp);
        }

        private static LocalDateTime getLocalDateTime(Timestamp timestamp) {
            return timestamp != null ? timestamp.toLocalDateTime() : null;
        }
}


