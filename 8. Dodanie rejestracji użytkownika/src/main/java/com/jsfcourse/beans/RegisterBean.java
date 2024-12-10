package com.jsfcourse.beans;

import com.jsfcourse.dao.RoleDAO;
import com.jsfcourse.dao.UserDAO;
import com.jsfcourse.entities.Role;
import com.jsfcourse.entities.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;

@Named
@RequestScoped
public class RegisterBean {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    @Inject
    private RoleDAO roleDAO;
    @Inject
    private UserDAO userDAO;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public String register() {
        if (!password.equals(confirmPassword)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User();
        user.setName(username);
        user.setSurname("test");
        user.setEmail(email);
        user.setPassword(hashedPassword);
        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        Role userRole = getRoleByName("admin");
        if (userRole != null) {
            user.setRoleId(userRole);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Role not found!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }

        try {
            userDAO.create(user);
            FacesMessage message = new FacesMessage("Registration successful!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "login.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            e.printStackTrace();
            return null;
        }
    }

    private Role getRoleByName(String name) {
        Role role = roleDAO.findByName(name);
        if (role == null) {
            throw new RuntimeException("Role not found: " + name);
        }
    return role;
}

}
