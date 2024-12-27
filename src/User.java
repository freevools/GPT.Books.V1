public class User {
    private String name;
    private String password;
    private boolean isAdmin;
    public User(String name, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public boolean getAdmission() {
        return isAdmin;
    }
    public void setAdmission(boolean admission) {
        this.isAdmin = admission;
    }

    @Override
    public String toString() {
        return getName() + ";" + getPassword() + ";" + getAdmission();
    }
}
