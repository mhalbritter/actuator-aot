package com.example.actuator_aot.hints;

import java.util.Set;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.util.ClassUtils;

/**
 * @author Moritz Halbritter
 */
public class LiquibaseRuntimeHints implements RuntimeHintsRegistrar {
    private static final Set<String> PRECONDITIONS = Set.of(
            "liquibase.precondition.CustomPreconditionWrapper",
            "liquibase.precondition.core.AndPrecondition",
            "liquibase.precondition.core.ChangeLogPropertyDefinedPrecondition",
            "liquibase.precondition.core.ChangeSetExecutedPrecondition",
            "liquibase.precondition.core.ColumnExistsPrecondition",
            "liquibase.precondition.core.DBMSPrecondition",
            "liquibase.precondition.core.ForeignKeyExistsPrecondition",
            "liquibase.precondition.core.IndexExistsPrecondition",
            "liquibase.precondition.core.NotPrecondition",
            "liquibase.precondition.core.ObjectQuotingStrategyPrecondition",
            "liquibase.precondition.core.OrPrecondition",
            "liquibase.precondition.core.PreconditionContainer",
            "liquibase.precondition.core.PrimaryKeyExistsPrecondition",
            "liquibase.precondition.core.RowCountPrecondition",
            "liquibase.precondition.core.RunningAsPrecondition",
            "liquibase.precondition.core.SequenceExistsPrecondition",
            "liquibase.precondition.core.SqlPrecondition",
            "liquibase.precondition.core.TableExistsPrecondition",
            "liquibase.precondition.core.TableIsEmptyPrecondition",
            "liquibase.precondition.core.UniqueConstraintExistsPrecondition",
            "liquibase.precondition.core.ViewExistsPrecondition"
    );

    private static final Set<String> CHANGES = Set.of(
            "liquibase.change.core.AddAutoIncrementChange",
            "liquibase.change.core.AddColumnChange",
            "liquibase.change.core.AddDefaultValueChange",
            "liquibase.change.core.AddForeignKeyConstraintChange",
            "liquibase.change.core.AddLookupTableChange",
            "liquibase.change.core.AddNotNullConstraintChange",
            "liquibase.change.core.AddPrimaryKeyChange",
            "liquibase.change.core.AddUniqueConstraintChange",
            "liquibase.change.core.AlterSequenceChange",
            "liquibase.change.core.CreateIndexChange",
            "liquibase.change.core.CreateProcedureChange",
            "liquibase.change.core.CreateSequenceChange",
            "liquibase.change.core.CreateTableChange",
            "liquibase.change.core.CreateViewChange",
            "liquibase.change.core.DeleteDataChange",
            "liquibase.change.core.DropAllForeignKeyConstraintsChange",
            "liquibase.change.core.DropColumnChange",
            "liquibase.change.core.DropDefaultValueChange",
            "liquibase.change.core.DropForeignKeyConstraintChange",
            "liquibase.change.core.DropIndexChange",
            "liquibase.change.core.DropNotNullConstraintChange",
            "liquibase.change.core.DropPrimaryKeyChange",
            "liquibase.change.core.DropProcedureChange",
            "liquibase.change.core.DropSequenceChange",
            "liquibase.change.core.DropTableChange",
            "liquibase.change.core.DropUniqueConstraintChange",
            "liquibase.change.core.DropViewChange",
            "liquibase.change.core.EmptyChange",
            "liquibase.change.core.ExecuteShellCommandChange",
            "liquibase.change.core.InsertDataChange",
            "liquibase.change.core.LoadDataChange",
            "liquibase.change.core.LoadUpdateDataChange",
            "liquibase.change.core.MergeColumnChange",
            "liquibase.change.core.ModifyDataTypeChange",
            "liquibase.change.core.OutputChange",
            "liquibase.change.core.RawSQLChange",
            "liquibase.change.core.RenameColumnChange",
            "liquibase.change.core.RenameSequenceChange",
            "liquibase.change.core.RenameTableChange",
            "liquibase.change.core.RenameViewChange",
            "liquibase.change.core.SQLFileChange",
            "liquibase.change.core.SetColumnRemarksChange",
            "liquibase.change.core.SetTableRemarksChange",
            "liquibase.change.core.StopChange",
            "liquibase.change.core.TagDatabaseChange",
            "liquibase.change.core.UpdateDataChange",
            "liquibase.change.custom.CustomChangeWrapper",
            "com.datical.liquibase.ext.appdba.markunused.change.MarkUnsedChange",
            "com.datical.liquibase.ext.appdba.synonym.change.CreateSynonymChange",
            "com.datical.liquibase.ext.appdba.synonym.change.DropSynonymChange",
            "com.datical.liquibase.ext.storedlogic.checkconstraint.change.AddCheckConstraintChange",
            "com.datical.liquibase.ext.storedlogic.checkconstraint.change.DisableCheckConstraintChange",
            "com.datical.liquibase.ext.storedlogic.checkconstraint.change.DropCheckConstraintChange",
            "com.datical.liquibase.ext.storedlogic.checkconstraint.change.EnableCheckConstraintChange",
            "com.datical.liquibase.ext.storedlogic.databasepackage.change.CreatePackageBodyChange",
            "com.datical.liquibase.ext.storedlogic.databasepackage.change.CreatePackageChange",
            "com.datical.liquibase.ext.storedlogic.databasepackage.change.DropPackageBodyChange",
            "com.datical.liquibase.ext.storedlogic.databasepackage.change.DropPackageChange",
            "com.datical.liquibase.ext.storedlogic.function.change.CreateFunctionChange",
            "com.datical.liquibase.ext.storedlogic.function.change.DropFunctionChange",
            "com.datical.liquibase.ext.storedlogic.trigger.change.CreateTriggerChange",
            "com.datical.liquibase.ext.storedlogic.trigger.change.DisableTriggerChange",
            "com.datical.liquibase.ext.storedlogic.trigger.change.DropTriggerChange",
            "com.datical.liquibase.ext.storedlogic.trigger.change.EnableTriggerChange",
            "com.datical.liquibase.ext.storedlogic.trigger.change.RenameTriggerChange"
    );

    private static final Set<String> ANNOTATIONS = Set.of(
            "liquibase.change.DatabaseChange",
            "liquibase.change.DatabaseChangeProperty",
            "liquibase.change.DatabaseChangeNote",
            "liquibase.datatype.DataTypeInfo"
    );

    private static final Set<String> TYPES = Set.of(
            "liquibase.datatype.core.BigIntType",
            "liquibase.datatype.core.BlobType",
            "liquibase.datatype.core.BooleanType",
            "liquibase.datatype.core.CharType",
            "liquibase.datatype.core.ClobType",
            "liquibase.datatype.core.CurrencyType",
            "liquibase.datatype.core.DatabaseFunctionType",
            "liquibase.datatype.core.DateTimeType",
            "liquibase.datatype.core.DateType",
            "liquibase.datatype.core.DecimalType",
            "liquibase.datatype.core.DoubleType",
            "liquibase.datatype.core.FloatType",
            "liquibase.datatype.core.IntType",
            "liquibase.datatype.core.MediumIntType",
            "liquibase.datatype.core.NCharType",
            "liquibase.datatype.core.NumberType",
            "liquibase.datatype.core.NVarcharType",
            "liquibase.datatype.core.SmallIntType",
            "liquibase.datatype.core.TimestampType",
            "liquibase.datatype.core.TimeType",
            "liquibase.datatype.core.TinyIntType",
            "liquibase.datatype.core.UnknownType",
            "liquibase.datatype.core.UUIDType",
            "liquibase.datatype.core.VarcharType",
            "liquibase.datatype.core.XMLType"
    );

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        if (!ClassUtils.isPresent("liquibase.Liquibase", classLoader)) {
            return;
        }
        hints.reflection().registerType(TypeReference.of("liquibase.configuration.LiquibaseConfiguration"), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS));
        hints.reflection().registerType(TypeReference.of("liquibase.logging.core.LogServiceFactory"), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS));
        hints.reflection().registerType(TypeReference.of("liquibase.license.LicenseServiceFactory"), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS));
        hints.reflection().registerType(TypeReference.of("liquibase.change.ChangeFactory"), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS));
        hints.reflection().registerType(TypeReference.of("liquibase.hub.HubServiceFactory"), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS));
        hints.reflection().registerType(TypeReference.of("liquibase.database.jvm.JdbcConnection"), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_METHODS));
        hints.reflection().registerType(TypeReference.of("liquibase.executor.ExecutorService"), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS));
        hints.reflection().registerType(TypeReference.of("liquibase.change.ColumnConfig"), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS));
        hints.reflection().registerType(TypeReference.of("liquibase.pro.packaged.gy"), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS));
        PRECONDITIONS.forEach(precondition -> hints.reflection().registerType(TypeReference.of(precondition), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.DECLARED_FIELDS, MemberCategory.INVOKE_DECLARED_METHODS)));
        CHANGES.forEach(change -> hints.reflection().registerType(TypeReference.of(change), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.DECLARED_FIELDS, MemberCategory.INVOKE_PUBLIC_METHODS)));
        ANNOTATIONS.forEach(annotation -> hints.reflection().registerType(TypeReference.of(annotation), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_METHODS)));
        TYPES.forEach(type -> hints.reflection().registerType(TypeReference.of(type), hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_METHODS)));
        hints.resources().registerPattern("db/changelog/db.changelog-master.yaml");
    }
}
