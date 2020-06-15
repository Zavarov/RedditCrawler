/*
 * Copyright (c) 2020 Zavarov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package vartas.reddit;

import org.apache.http.client.HttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class PushshiftClient extends JrawClient{
    /**
     * This classes logger.
     */
    private final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

    public PushshiftClient(String redditName, String version, String clientId, String secret){
        super(redditName, version, clientId, secret);
    }

    public Subreddit requestSubreddit(String subredditName) throws TimeoutException, UnsuccessfulRequestException, HttpResponseException {
        log.info("Requesting subreddit {}", subredditName);
        return PushshiftSubreddit.create(
                () -> new PushshiftSubreddit(jrawClient),
                request(() -> Optional.of(jrawClient.subreddit(subredditName).about()), 0),
                jrawClient
        );
    }
}