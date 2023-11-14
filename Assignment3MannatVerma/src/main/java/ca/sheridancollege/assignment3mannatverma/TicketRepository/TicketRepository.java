package ca.sheridancollege.assignment3mannatverma.TicketRepository;

import ca.sheridancollege.assignment3mannatverma.Ticket.Ticket;
import ca.sheridancollege.assignment3mannatverma.User.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class TicketRepository {
    private NamedParameterJdbcTemplate jdbc;
    public ArrayList<Ticket> getickets() {
        MapSqlParameterSource paramenters = new MapSqlParameterSource();
        String query = "select * from tickets";
        ArrayList<Ticket> tickets =
                (ArrayList<Ticket>) jdbc.query(query, paramenters,
                        new BeanPropertyRowMapper<Ticket>(Ticket.class));
        return tickets;
    }
    public Ticket getTicketById(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM tickets where ID= :woof";
        parameters.addValue("woof", id);
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) jdbc.query(query, parameters, new BeanPropertyRowMapper<Ticket>(Ticket.class));

        if (tickets.size() > 0) {
            return tickets.get(0);
        } else {
            return null;
        }
    }

    public void editTicket(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "UPDATE TICKETS SET name=:na, price= :p, seatNumber=:sn, "
                + " date=:d, time=:t where id= :woof";
        parameters.addValue("na", ticket.getName());
        parameters.addValue("p", ticket.getPrice());
        parameters.addValue("sn",ticket.getSeatNumber());
        parameters.addValue("d",ticket.getDate());
        parameters.addValue("t",ticket.getTime());
        parameters.addValue("woof", ticket.getId());
        jdbc.update(query, parameters);

    }

    public void deleteTickets(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "Delete From tickets where id = :woof";

        parameters.addValue("woof",ticket.getId());

        jdbc.update(query, parameters);
    }

    public void addTicket(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO tickets (name, price, seatNumber, date, time) "
                + "VALUES (:na, :p, :sn, :d, :t)";
        parameters.addValue("na", ticket.getName());
        parameters.addValue("p", ticket.getPrice());
        parameters.addValue("sn", ticket.getSeatNumber());
        parameters.addValue("d", ticket.getDate());
        parameters.addValue("t", ticket.getTime());
        jdbc.update(query, parameters);
    }

    public void addNewUser(String userName, String password) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String query="insert into SEC_User (userName, encryptedPassword, ENABLED) " +
                "values (:uname, :ep, 1)";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        parameterSource.addValue("uname",userName);
        parameterSource.addValue("ep",passwordEncoder.encode(password));
        jdbc.update(query,parameterSource);
    }
    public ArrayList<User> getUser() {
        MapSqlParameterSource paramenters = new MapSqlParameterSource();
        String query = "SELECT * FROM SEC_USER ";
        ArrayList<User> users =
                (ArrayList<User>) jdbc.query(query, paramenters,
                        new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
    public User getUserByUsername(String userName) {
        MapSqlParameterSource paramenters = new MapSqlParameterSource();
        String query = "SELECT * FROM SEC_USER Where userName=:woof ";
        paramenters.addValue("woof",userName);
        ArrayList<User> users =
                (ArrayList<User>) jdbc.query(query, paramenters,
                        new BeanPropertyRowMapper<User>(User.class));
        if (users.size()>0)
        {
            return users.get(0);
        }
        else
            return null;
    }


    public void addNewRole(long userId, long roleId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String query="insert into user_role (userId, roleId)\n" +
                "values (:uid, :rid);";
        parameterSource.addValue("uid",userId);
        parameterSource.addValue("rid",roleId);
        jdbc.update(query,parameterSource);
    }
    public List<String> getRolesById(long userId){
        MapSqlParameterSource paramenters = new MapSqlParameterSource();
        String query="SELECT user_role.userId, sec_role.roleName from USER_ROLE  , sec_role where user_role.roleId=sec_role.roleId and userId=2";
        paramenters.addValue("woof",userId);
        ArrayList<String> roles = new ArrayList<String>();
        List<Map<String, Object>> rows = jdbc.queryForList(query,paramenters);
        for (Map<String, Object> row : rows)
        {
            roles.add((String)row.get("roleName"));
        }
        return roles;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<String> getGuestUsernames() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT userName FROM SEC_USER INNER JOIN USER_ROLE " +
                "ON SEC_USER.userId = USER_ROLE.userId" +
                " WHERE USER_ROLE.roleId = 2 ORDER BY userName";
        return jdbc.queryForList(query, parameters, String.class);

    }
}
