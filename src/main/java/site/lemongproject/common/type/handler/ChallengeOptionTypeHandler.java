package site.lemongproject.common.type.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import site.lemongproject.web.challenge.model.dto.ChallengeOption;

import java.sql.*;
import java.time.LocalDate;

@MappedTypes(ChallengeOption.class)
public class ChallengeOptionTypeHandler extends BaseTypeHandler<ChallengeOption> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, ChallengeOption parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex,ChallengeOption.parseOption(parameter));
    }

    @Override
    public ChallengeOption getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String option = rs.getString(columnName);
        if (option != null) {
            return ChallengeOption.getInstance(option);
        }
        return null;
    }

    @Override
    public ChallengeOption getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String option = rs.getString(columnIndex);
        if (option != null) {
            return ChallengeOption.getInstance(option);
        }
        return null;
    }

    @Override
    public ChallengeOption getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String option = cs.getString(columnIndex);
        if (option != null) {
            return ChallengeOption.getInstance(option);
        }
        return null;
    }
}