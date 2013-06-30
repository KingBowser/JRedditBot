package pn.us.hysteria.redditbot;

import com.omrlnr.jreddit.User;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Creator: KingBowser
 * Date: 6/29/13
 * Time: 11:59 PM
 * Refer to LICENSE for licensing information
 */
public final class JRedditBot {

    // Variables

    private Configuration botConfiguration;
    private Logger logServicesProvider;

    private final User botUser;

    // Constructors

    public JRedditBot(File configurationFile) {

        // Start with an nondescript logger before we can get more info about who we are
        logServicesProvider = LogManager.getLogger("jReddit");

        // Use a configuration factory to get the proper configuration implementation for the configuration file
        getLogServicesProvider().info("Attempting to load configuration");
        try {

            DefaultConfigurationBuilder configurationBuilder = new DefaultConfigurationBuilder(configurationFile);
            this.botConfiguration = configurationBuilder.getConfiguration(!configurationFile.exists());

        } catch (ConfigurationException e) {

            getLogServicesProvider().fatal("Failed to initialize bot configuration stored at " + configurationFile.getAbsolutePath());
            getLogServicesProvider().catching(Level.FATAL, e);
            System.exit(-1);

        }

        // Perform a sanity check to insure that the configuration contains a user
        getLogServicesProvider().debug("Making sure that the configuration contains credentials");
        if (!(getBotConfiguration().containsKey(ConfigurationProperties.USERNAME.key)
                || getBotConfiguration().containsKey(ConfigurationProperties.PASSWORD.key)))
            throw new IllegalStateException("Configuration does not contain a username and/or password.");

        // Now that we're still alive, we must be someone
        getLogServicesProvider().debug("Re-Initializing the logger with a properly named instance");
        logServicesProvider = LogManager.getLogger("jReddit(" + getBotConfiguration().getString(ConfigurationProperties.USERNAME.key) + ")");

        // Configure the user object, and connect
        getLogServicesProvider().debug("Configuring User");
        botUser = new User(getBotConfiguration().getString(ConfigurationProperties.USERNAME.key),
                           getBotConfiguration().getString(ConfigurationProperties.PASSWORD.key));

        getLogServicesProvider().info("Connecting to reddit");
        try {
            botUser.connect();
        } catch (Exception e) {
            getLogServicesProvider().fatal("Unable to connect to reddit");
            getLogServicesProvider().catching(e);
            System.exit(-1);
        }


    }

    // Methods

    /**
     * Get the Logger in use
     * @return Logger
     */
    public Logger getLogServicesProvider() {
        return logServicesProvider;
    }

    /**
     * Get the bot Configuration
     * @return Configuration
     */
    public Configuration getBotConfiguration() {
        return botConfiguration;
    }

    /**
     * Get the User used by the Bot
     * @return User
     */
    public User getBotUser() {
        return botUser;
    }

}
