package logic;

import dao.UserDAO;
import entities.User;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.vraptor.annotations.Component;
import org.vraptor.annotations.In;
import org.vraptor.annotations.Out;
import org.vraptor.annotations.Parameter;

@Component("providers")
public class ProviderLogic {

    @In
    private HttpServletRequest request;

    @Out
    private String msgs;

    // Method to goto provider options page
    public void options() {}
}