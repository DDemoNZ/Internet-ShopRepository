package mate.academy.internetshop.lib;

public class RoleIdGenerator {
    private static Long idGenerator = 0L;

    private RoleIdGenerator() {

    }

    public static Long getIdGenerator() {
        return idGenerator++;
    }
}
