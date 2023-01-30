package site.lemongproject.common.type.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
@MappedTypes(NameEnum.class)
public class NameEnumTypeHandler<E extends Enum<E> & NameEnum> implements TypeHandler<NameEnum> {
        private Class<E> type;

        public NameEnumTypeHandler(Class<E> type) {
            this.type = type;
        }

        @Override
        public void setParameter(PreparedStatement ps, int i, NameEnum parameter, JdbcType jdbcType) throws SQLException {
            ps.setString(i, parameter.getName());
        }

        @Override
        public NameEnum getResult(ResultSet rs, String columnName) throws SQLException {
            return getCodeEnum(rs.getString(columnName));
        }

        @Override
        public NameEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
            return getCodeEnum(rs.getString(columnIndex));
        }

        @Override
        public NameEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
            return getCodeEnum(cs.getString(columnIndex));
        }

        private NameEnum getCodeEnum(String code) {
            return EnumSet.allOf(type)
                    .stream()
                    .filter(value -> value.getName().equals(code))
                    .findFirst()
                    .orElseGet(null);
        }
}
