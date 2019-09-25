package base.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


// INSERT INTO db_example.news (id, headline, description) values(1, 'EURONEWS', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed viverra.');
@Entity
@Table(name="news")
public class News implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "headline")
    private String headline;

    @Column(name="description")
    private String description;
    // INSERT INTO db_example.news_comments (news_id, comments_id) values(1, 1);
    // INSERT INTO db_example.news_comments (news_id, comments_id) values(1, 2);
    // INSERT INTO db_example.news_comments (news_id, comments_id) values(2, 3);
    // INSERT INTO db_example.news_comments (news_id, comments_id) values(3, 4);
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "news_comments", joinColumns = {@JoinColumn(name = "news_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "comments_id", referencedColumnName = "id")})
    private List<Comment> comments;

    public News(){

    }

    public News(String headline, String description) {
        this.headline = headline;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
