/*
 * Copyright (c) 2019 Zavarov
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

package vartas.reddit.compact;

import vartas.reddit.Comment;
import vartas.reddit.Submission;

import java.time.LocalDateTime;
import java.util.Optional;

public class CompactSubmission implements Submission {
    protected String linkFlairText;
    protected boolean isNsfw;
    protected boolean isSpoiler;
    protected String title;
    protected String selftext;
    protected String thumbnail;
    protected String url;
    protected String author;
    protected String id;
    protected int score;
    protected String subreddit;
    protected LocalDateTime created;

    protected CompactSubmission(){}

    public static CompactSubmission copyOf(Submission comment){
        CompactSubmission copy = new CompactSubmission();
        copy.linkFlairText = comment.getLinkFlairText().orElse(null);
        copy.isNsfw = comment.isNsfw();
        copy.isSpoiler = comment.isSpoiler();
        copy.title = comment.getTitle();
        copy.selftext = comment.getSelfText().orElse(null);
        copy.thumbnail = comment.getThumbnail().orElse(null);
        copy.url = comment.getUrl();
        copy.author = comment.getAuthor();
        copy.id = comment.getId();
        copy.score = comment.getScore();
        copy.subreddit = comment.getSubreddit();
        copy.created = comment.getCreated();
        return copy;
    }

    @Override
    public Optional<String> getLinkFlairText() {
        return Optional.ofNullable(linkFlairText);
    }

    @Override
    public boolean isNsfw() {
        return isNsfw;
    }

    @Override
    public boolean isSpoiler() {
        return isSpoiler;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Optional<String> getSelfText() {
        return Optional.ofNullable(selftext);
    }

    @Override
    public Optional<String> getThumbnail() {
        return Optional.ofNullable(thumbnail);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getSubreddit() {
        return subreddit;
    }

    @Override
    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public int hashCode(){
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Comment){
            Comment comment = (Comment)o;
            return comment.getId().equals(this.getId());
        }else{
            return false;
        }
    }
}
