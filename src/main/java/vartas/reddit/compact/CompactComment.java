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

import java.time.LocalDateTime;

public class CompactComment implements Comment{
    private String submission;
    private String submissionTitle;
    private String author;
    private String id;
    private String subreddit;
    private int score;
    private LocalDateTime created;

    private CompactComment(){}

    public static CompactComment copyOf(Comment comment){
        CompactComment copy = new CompactComment();
        copy.submission = comment.getSubmission();
        copy.submissionTitle = comment.getSubmissionTitle();
        copy.author = comment.getAuthor();
        copy.id = comment.getId();
        copy.subreddit = comment.getSubreddit();
        copy.score = comment.getScore();
        copy.created = comment.getCreated();
        return copy;
    }

    @Override
    public String getSubmission() {
        return submission;
    }

    @Override
    public String getSubmissionTitle() {
        return submissionTitle;
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
}
