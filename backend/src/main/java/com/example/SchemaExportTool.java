import org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator;
import org.hibernate.boot.Metadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.schema.spi.DelayedDropRegistry;
import java.util.HashMap;

public class SchemaExportTool {
    public static void exportSchema(Metadata metadata, ServiceRegistry serviceRegistry) {
        SchemaManagementToolCoordinator.process(
            metadata,
            serviceRegistry,
            new HashMap<>(), // Corrected argument
            (DelayedDropRegistry) null // Corrected argument
        );
    }
}
