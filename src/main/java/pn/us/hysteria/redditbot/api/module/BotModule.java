package pn.us.hysteria.redditbot.api.module;

import pn.us.hysteria.redditbot.JRedditBot;

/**
 * Creator: KingBowser
 * Date: 6/30/13
 * Time: 10:03 AM
 * Refer to LICENSE for licensing information
 */
public abstract class BotModule {

    private JRedditBot bot;

    /**
     * Get the name of the module
     * @return module name
     */
    public abstract String getModuleName();

    /**
     * Get the version of the module
     * @return module version
     */
    public abstract short getModuleVersion();

    /**
     * Initialize the module
     */
    public abstract void initialize();

    /*
     * JRedditBot instance manager
     */

    /**
     * Get the instance of JRedditBot in use
     * @return bot instance
     */
    protected final JRedditBot getBot() {
        return bot;
    }

    /**
     * Set the instance
     * @param bot instance of the bot
     */
    final void setBot(JRedditBot bot) {

        if(this.bot != null)
            throw new IllegalStateException("Module has already been configured");

        this.bot = bot;

    }

}
