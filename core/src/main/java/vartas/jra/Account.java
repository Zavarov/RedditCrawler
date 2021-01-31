package vartas.jra;

import org.json.JSONObject;
import vartas.jra.query.*;
import vartas.jra.types.$json.JSONUser;
import vartas.jra.types.Thing;
import vartas.jra.types.TrophyList;
import vartas.jra.types.User;

import javax.annotation.Nonnull;
import java.util.function.Function;

public class Account extends AccountTOP {
    /**
     * A reference to the client required for communicating with the account-specific endpoints.
     */
    @Nonnull
    private final Client client;
    @Nonnull
    private final JSONObject source;

    /**
     * Creates a new instance of an account.
     * @param client The client communicating with the endpoints.
     */
    @Nonnull
    public Account(@Nonnull Client client, @Nonnull JSONObject source){
        this.client = client;
        this.source = source;
    }

    @Override
    @Nonnull
    public QueryOne<TrophyList> getTrophies() {
        return new QueryOne<>(
                source -> Thing.from(source).toTrophyList(),
                client,
                Endpoint.GET_USER_USERNAME_TROHPIES,
                getName()
        );
    }

    @Override
    @Nonnull
    public QueryMany<Comment> getComments() {
        return new QueryMany<>(Thing::toComment, client, Endpoint.GET_USER_USERNAME_COMMENTS, getName());
    }

    @Override
    @Nonnull
    public QueryMany<Thing> getDownvoted() {
        return new QueryMany<>(Function.identity(), client, Endpoint.GET_USER_USERNAME_DOWNVOTED, getName());
    }

    @Override
    @Nonnull
    public QueryMany<Thing> getGilded() {
        return new QueryMany<>(Function.identity(), client, Endpoint.GET_USER_USERNAME_GILDED, getName());
    }

    @Override
    @Nonnull
    public QueryMany<Thing> getHidden() {
        return new QueryMany<>(Function.identity(), client, Endpoint.GET_USER_USERNAME_HIDDEN, getName());
    }

    @Override
    @Nonnull
    public QueryMany<Thing> getOverview() {
        return new QueryMany<>(Function.identity(), client, Endpoint.GET_USER_USERNAME_OVERVIEW, getName());
    }

    @Override
    @Nonnull
    public QueryMany<Thing> getSaved() {
        return new QueryMany<>(Function.identity(), client, Endpoint.GET_USER_USERNAME_SAVED, getName());
    }

    @Override
    @Nonnull
    public QueryMany<Link> getSubmitted() {
        return new QueryMany<>(Thing::toLink, client, Endpoint.GET_USER_USERNAME_SUBMITTED, getName());
    }

    @Override
    @Nonnull
    public QueryMany<Thing> getUpvoted() {
        return new QueryMany<>(Function.identity(), client, Endpoint.GET_USER_USERNAME_UPVOTED, getName());
    }

    /**
     * For blocking the current account.<p>
     * This endpoint accepts the following arguments:
     * <table>
     *     <tr>
     *         <th>{@code account_id}</th>
     *         <th>{@code fullname} of the account blocking</th>
     *     </tr>
     *     <tr>
     *         <th>{@code api_type}</th>
     *         <th>The string {@code json}</th>
     *     </tr>
     *     <tr>
     *         <th>{@code name}</th>
     *         <th>The valid, existing reddit username{@code fullname} of the blocked account</th>
     *     </tr>
     *     <tr>
     *         <th>{@code uh / X-Modhash header}</th>
     *         <th>a modhash</th>
     *     </tr>
     * </table>
     */
    @Override
    public QueryPost<String> postBlock() {
        QueryPost<String> query = new QueryPost<>(Function.identity(), client, Endpoint.POST_BLOCK_USER);
        query.setParameter("account_id", getId());
        return query;
    }
    /**
     * Create a relationship between a user and another user or subreddit.<p>
     * OAuth2 use requires appropriate scope based on the 'type' of the relationship:<p>
     * <ul>
     *     <li>moderator: Use "moderator_invite"</li>
     *     <li>moderator_invite: modothers</li>
     *     <li>contributor: modcontributors</li>
     *     <li>banned: modcontributors</li>
     *     <li>muted: modcontributors</li>
     *     <li>wikibanned: modcontributors and modwiki</li>
     *     <li>wikicontributor: modcontributors and modwiki</li>
     *     <li>friend: Use /api/v1/me/friends/{username}</li>
     *     <li>enemy: Use /api/block</li>
     * </ul>
     * This endpoint accepts the following arguments:
     * <table>
     *     <tr>
     *         <th>{@code api_type}</th>
     *         <th>The string {@code json}</th>
     *     </tr>
     *     <tr>
     *         <th>{@code ban_context}</th>
     *         <th>fullname of a thing</th>
     *     </tr>
     *     <tr>
     *         <th>{@code ban_message}</th>
     *         <th>raw markdown text</th>
     *     </tr>
     *     <tr>
     *         <th>{@code ban_reason}</th>
     *         <th>a string no longer than 100 characters</th>
     *     </tr>
     *     <tr>
     *         <th>{@code container}</th>
     *         <th>e.g. the subreddit fullname</th>
     *     </tr>
     *     <tr>
     *         <th>{@code duration}</th>
     *         <th>an integer between 1 and 999</th>
     *     </tr>
     *     <tr>
     *         <th>{@code name}</th>
     *         <th>the name of an existing user</th>
     *     </tr>
     *     <tr>
     *         <th>{@code note}</th>
     *         <th>a string no longer than 300 characters</th>
     *     </tr>
     *     <tr>
     *         <th>{@code permissions}</th>
     *         <th></th>
     *     </tr>
     *     <tr>
     *         <th>{@code type}</th>
     *         <th>	one of ({@code friend}, {@code moderator}, {@code moderator_invite}, {@code contributor},
     *         {@code banned}, {@code muted}, {@code wikibanned}, {@code wikicontributor})</th>
     *     </tr>
     *     <tr>
     *         <th>{@code uh / X-Modhash header}</th>
     *         <th>a modhash</th>
     *     </tr>
     * </table>
     * Complement to {@link #postUnfriend()}
     * @see #postUnfriend()
     */
    @Override
    public QueryPost<String> postFriend() {
        QueryPost<String> q = new QueryPost<>(Function.identity(), client, Endpoint.POST_FRIEND);
        q.setParameter("name", getName());
        return q;
    }

    /**
     * Report a user. Reporting a user brings it to the attention of a Reddit admin.<p>
     * This endpoint accepts the following arguments:
     * <table>
     *     <tr>
     *         <th>{@code details}</th>
     *         <th>JSON data</th>
     *     </tr>
     *     <tr>
     *         <th>{@code reason}</th>
     *         <th>a string no longer than 100 characters</th>
     *     </tr>
     *     <tr>
     *         <th>{@code ('user',)}</th>
     *         <th>A valid, existing reddit username</th>
     *     </tr>
     * </table>
     */
    @Override
    public QueryPost<String> postReport() {
        QueryPost<String> q = new QueryPost<>(Function.identity(), client, Endpoint.POST_REPORT_USER);
        q.setParameter("user", getName());
        return q;
    }

    /**
     * This endpoint accepts the following arguments:
     * <table>
     *     <tr>
     *         <th>{@code api_type}</th>
     *         <th>the string {@code json}</th>
     *     </tr>
     *     <tr>
     *         <th>{@code name}</th>
     *         <th>the name of an existing user</th>
     *     </tr>
     *     <tr>
     *         <th>{@code permission}</th>
     *         <th></th>
     *     </tr>
     *     <tr>
     *         <th>{@code type}</th>
     *         <th></th>
     *     </tr>
     *     <tr>
     *         <th>{@code uh / X-Modhash header}</th>
     *         <th>a modhash</th>
     *     </tr>
     * </table>
     */
    @Override
    public QueryPost<String> postSetPermission() {
        QueryPost<String> q = new QueryPost<>(Function.identity(), client, Endpoint.POST_SETPERMISSION);
        q.setParameter("name", getName());
        return q;
    }

    /**
     * Remove a relationship between a user and another user or subreddit<p>
     * The user can either be passed in by name (nuser) or by fullname (iuser). If type is friend or enemy, 'container'
     * MUST be the current user's fullname; for other types, the subreddit must be set via URL
     * (e.g., /r/funny/api/unfriend).<p>
     * OAuth2 use requires appropriate scope based on the 'type' of the relationship:
     * <ul>
     *     <li>moderator: modothers</li>
     *     <li>moderator_invite: modothers</li>
     *     <li>contributor: modcontributors</li>
     *     <li>banned: modcontributors</li>
     *     <li>muted: modcontributors</li>
     *     <li>wikibanned: modcontributors and modwiki</li>
     *     <li>wikicontributor: modcontributors and modwiki</li>
     *     <li>friend: Use /api/v1/me/friends/{username}</li>
     *     <li>enemy: privatemessages<li>
     * </ul>
     * This endpoint accepts the following arguments:
     * <table>
     *     <tr>
     *         <th>{@code container}</th>
     *         <th></th>
     *     </tr>
     *     <tr>
     *         <th>{@code id}</th>
     *         <th>fullname of a thing</th>
     *     </tr>
     *     <tr>
     *         <th>{@code name}</th>
     *         <th>the name of an existing user</th>
     *     </tr>
     *     <tr>
     *         <th>{@code type}</th>
     *         <th>	one of ({@code friend}, {@code enemy}, {@code moderator}, {@code moderator_invite},
     *         {@code contributor}, {@code banned}, {@code muted}, {@code wikibanned}, {@code wikicontributor})</th>
     *     </tr>
     *     <tr>
     *         <th>{@code uh / X-Modhash header}</th>
     *         <th>a modhash</th>
     *     </tr>
     * </table>
     *
     * Complement to {@link #postFriend()}
     * @see #postFriend()
     */
    @Override
    public QueryPost<String> postUnfriend() {
        QueryPost<String> q = new QueryPost<>(Function.identity(), client, Endpoint.POST_UNFRIEND);
        q.setParameter("name", getName());
        return q;
    }

    /**
     * Stop being friends with a user.<p>
     * This endpoint accepts the following arguments:
     * <table>
     *     <tr>
     *         <th>{@code id}</th>
     *         <th>A valid, existing reddit username</th>
     *     </tr>
     * </table>
     */
    @Override
    public QueryDelete<Void> deleteFriends() {
        return new QueryDelete<>(x -> null, client, Endpoint.DELETE_ME_FRIENDS_USERNAME, getName());
    }

    /**
     * Get information about a specific 'friend', such as notes.<p>
     * This endpoint accepts the following arguments:
     * <table>
     *     <tr>
     *         <th>{@code id}</th>
     *         <th>A valid, existing reddit username</th>
     *     </tr>
     * </table>
     */
    @Override
    public QueryOne<User> getFriends() {
        return new QueryOne<>(
                source -> JSONUser.fromJson(new User(), source),
                client,
                Endpoint.GET_ME_FRIENDS_USERNAME,
                getName()
        );
    }

    /**
     * Create or update a "friend" relationship.<p>
     * This operation is idempotent. It can be used to add a new friend, or update an existing friend
     * (e.g., add/change the note on that friend).<p>
     * This endpoint accepts the following arguments:
     * <table>
     *     <tr>
     *         <th>{@code name}</th>
     *         <th>A valid, existing reddit username</th>
     *     </tr>
     *     <tr>
     *         <th>{@code note}</th>
     *         <th>A string no longer than 300 characters</th>
     *     </tr>
     * </table>
     */
    @Override
    public QueryPut<User> putFriends() {
        return new QueryPut<>(
                source -> JSONUser.fromJson(new User(), source),
                client,
                Endpoint.PUT_ME_FRIENDS_USERNAME,
                getName()
        );
    }

    @Override
    @Nonnull
    public JSONObject getSource() {
        return source;
    }

    @Override
    public Account getRealThis() {
        return this;
    }
}