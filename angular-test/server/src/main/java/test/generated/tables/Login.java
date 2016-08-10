/**
 * This class is generated by jOOQ
 */
package test.generated.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;

import test.generated.Keys;
import test.generated.Test;
import test.generated.tables.records.LoginRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Login extends TableImpl<LoginRecord> {

    private static final long serialVersionUID = 523685453;

    /**
     * The reference instance of <code>Test.login</code>
     */
    public static final Login LOGIN = new Login();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LoginRecord> getRecordType() {
        return LoginRecord.class;
    }

    /**
     * The column <code>Test.login.username</code>.
     */
    public final TableField<LoginRecord, String> USERNAME = createField("username", org.jooq.impl.SQLDataType.VARCHAR.length(45).nullable(false), this, "");

    /**
     * The column <code>Test.login.password</code>.
     */
    public final TableField<LoginRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR.length(45).nullable(false), this, "");

    /**
     * Create a <code>Test.login</code> table reference
     */
    public Login() {
        this("login", null);
    }

    /**
     * Create an aliased <code>Test.login</code> table reference
     */
    public Login(String alias) {
        this(alias, LOGIN);
    }

    private Login(String alias, Table<LoginRecord> aliased) {
        this(alias, aliased, null);
    }

    private Login(String alias, Table<LoginRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Test.TEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<LoginRecord> getPrimaryKey() {
        return Keys.KEY_LOGIN_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<LoginRecord>> getKeys() {
        return Arrays.<UniqueKey<LoginRecord>>asList(Keys.KEY_LOGIN_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Login as(String alias) {
        return new Login(alias, this);
    }

    /**
     * Rename this table
     */
    public Login rename(String name) {
        return new Login(name, null);
    }
}
