package pn.us.hysteria.redditbot;

/**
 * Creator: KingBowser
 * Date: 6/30/13
 * Time: 12:56 AM
 * Refer to LICENSE for licensing information
 */
public enum ConfigurationProperties {

    /**
     * Bot username
     */
    USERNAME("username"),
    /**
     * Bot password
     */
    PASSWORD("password"),

    /**
     * List of usernames to watch for activity
     */
    WATCH_USERS("watch.users"),
    /**
     * List of subreddits to watch for activity
     */
    WATCH_SUBREDDITS("watch.subreddits"),

    /**
     * Whether or not the bot should pay attention to private messages
     */
    IGNORE_MESSAGES("messages.ignore"),
    /**
     * Whether or not the bot should pay attention to comment responses
     */
    IGNORE_RESPONSE("comments.response.ignore"),

    /**
     * How often the bot should check for activity & process it
     */
    QUERY_INTERVAL("bot.query_interval"),

    /**
     * The maximum age for a post if it is to be processed
     */
    MAX_POST_AGE("reddit.post.age_threshold"),
    /**
     * The maximum age for a comment if it is to be processed
     */
    MAX_COMMENT_AGE("reddit.comment.age_threshold");


    public final String key;

    ConfigurationProperties(String value){

        this.key = value;

    }
}
