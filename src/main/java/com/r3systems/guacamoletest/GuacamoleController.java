package com.r3systems.guacamoletest;

import org.glyptodon.guacamole.GuacamoleException;
import org.glyptodon.guacamole.net.GuacamoleSocket;
import org.glyptodon.guacamole.net.GuacamoleTunnel;
import org.glyptodon.guacamole.net.InetGuacamoleSocket;
import org.glyptodon.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.glyptodon.guacamole.protocol.GuacamoleConfiguration;
import org.glyptodon.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.glyptodon.guacamole.servlet.GuacamoleSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by R3Systems Pty Ltd
 * User tk421 on 4/9/14.
 */
public class GuacamoleController extends GuacamoleHTTPTunnelServlet {


    @Override
    protected GuacamoleTunnel doConnect(HttpServletRequest request)
            throws GuacamoleException {

        // Create our configuration
        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol("ssh");
        config.setParameter("hostname", "127.0.0.1");
        config.setParameter("port", "22");
        config.setParameter("username", "root");
        config.setParameter("password", "custompassword");

        // Connect to guacd - everything is hard-coded here.
        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket("localhost", 4822),
                config
        );

        // Establish the tunnel using the connected socket
        GuacamoleTunnel tunnel = new GuacamoleTunnel(socket);

        // Attach tunnel to session
        HttpSession httpSession = request.getSession(true);
        GuacamoleSession session = new GuacamoleSession(httpSession);
        session.attachTunnel(tunnel);

        // Return pre-attached tunnel
        return tunnel;

    }


}