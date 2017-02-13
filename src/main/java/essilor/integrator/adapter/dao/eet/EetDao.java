package essilor.integrator.adapter.dao.eet;


import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.EetData;
import essilor.integrator.adapter.service.ServiceCallTimestampHolder;
import essilor.integrator.adapter.service.eet.EetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EetDao {

    public static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return fmt;
        }
    };

    private JdbcTemplate jdbcTemplate;

    private static final String LOG_EET_RESULT = "insert into eet_log (write_time," +
            "                                                          user_name," +
            "                                                          send," +
            "                                                          method_name," +
            "                                                          uuid," +
            "                                                          dt_send," +
            "                                                          first_send," +
            "                                                          verification," +
            "                                                          processed," +
            "                                                          direction," +
            "                                                          ci_pd," +
            "                                                          skup_pd," +
            "                                                          skupina," +
            "                                                          fik," +
            "                                                          err_code," +
            "                                                          err_text," +
            "                                                          xml_input," +
            "                                                          xml_output," +
            "                                                          db_datasource)" +
            "                                    values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_FIK = "update se_pdvf set fik=?,pkp=?,bkp=? where ci_pdvf=? and skupina_sz=? and typ='PD' and skup_vfpd=?";

    @Autowired
    public EetDao(@Qualifier("dataSource")DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void logResult(final AdapterRequest request, final EetResult result) {
        this.jdbcTemplate.update(LOG_EET_RESULT,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps)
                            throws SQLException {
                        ps.setTimestamp(1, new Timestamp(ServiceCallTimestampHolder.getAsLong())); // WRITE_TIME
                        ps.setString(2, result.getUsername()); // USERNAME
                        ps.setString(3, "1"); // SEND
                        ps.setString(4, result.getMethodName()); // METHOD_NAME
                        ps.setString(5, ""); // UUID
                        ps.setString(
                                6,
                                dateFormat.get().format(
                                        new Date(ServiceCallTimestampHolder.getAsLong()))); // TIME_STAMP
                        ps.setString(7, result.getFirstSend()); // FIRST SEND
                        ps.setString(8, result.getVerification()); // VERIFICATION
                        ps.setInt(9, result.getProcessed()); // PROCESSED
                        ps.setString(10, result.getDirection()); // DIRECTION
                        ps.setLong(11, Long.valueOf(result.getOrderNumber())); // CI_PD
                        ps.setLong(12, Long.valueOf(result.getOrderGroup())); // SKUP_PD
                        ps.setLong(13, Long.valueOf(result.getPurchaseOrderNumber())); // SKUPINA
                        ps.setString(14,result.getFik()); // FIK
                        ps.setString(15, result.getErrorCode()); // ERROR_CODE
                        ps.setString(16, result.getErrorText()); // ERROR_TEXT
                        ps.setString(17, result.getXmlInput()); // XML_INPUT
                        ps.setString(18, result.getXmlOutput()); // XML_OUTPUT
                        ps.setString(19, request.getEetData().getDataSourceName()); // DB_DATASOURCE
                    }
                });

    }

    public void updateFik(final EetData eetData, final EetResult eetResult) {
        if (eetResult == null) {
            throw new IllegalArgumentException("eetResult is null");
        }
        if (eetData == null) {
            throw new IllegalArgumentException("eetData is null");
        }
        this.jdbcTemplate.update(UPDATE_FIK,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps)
                            throws SQLException {
                        ps.setString(1, eetResult.getFik()); // FIK
                        ps.setString(2, eetResult.getPkp()); // PKP
                        ps.setString(3, eetResult.getBkp()); // BKP
                        ps.setLong(4, Long.parseLong(eetData.getPoradoveCisloDokladu())); // CI_PDVF
                        ps.setInt(5, Integer.parseInt(eetData.getSkupinaZakazok())); // SKUPINA_SZ
                        ps.setString(6, eetData.getSkupinaDokladu()); // SKUP_VFPD
                    }
                });
    }
}
