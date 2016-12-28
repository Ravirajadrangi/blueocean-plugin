package io.jenkins.blueocean.blueocean_github_pipeline;

import com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials;
import io.jenkins.blueocean.rest.hal.Link;
import io.jenkins.blueocean.rest.impl.pipeline.scm.Scm;
import io.jenkins.blueocean.rest.impl.pipeline.scm.ScmOrganization;
import io.jenkins.blueocean.rest.impl.pipeline.scm.ScmRepositoryContainer;
import org.kohsuke.github.GHOrganization;

/**
 * @author Vivek Pandey
 */
public class GithubOrganization extends ScmOrganization {

    private final GHOrganization ghOrganization;
    private final Link self;
    private final StandardUsernamePasswordCredentials credential;
    private final Scm scm;

    public GithubOrganization(Scm scm, GHOrganization ghOrganization, StandardUsernamePasswordCredentials credential, Link parent) {
        this.scm = scm;
        this.ghOrganization = ghOrganization;
        this.credential = credential;
        this.self = parent.rel(ghOrganization.getLogin());
    }

    String getScmRootUrl(){
        return scm.getUri();
    }

    @Override
    public String getId() {
        return ghOrganization.getLogin();
    }

    @Override
    public ScmRepositoryContainer getRepositories() {
        return new GithubRespositoryContainer(this);
    }

    GHOrganization getGHOrganization() {
        return ghOrganization;
    }

    StandardUsernamePasswordCredentials getCredential() {
        return credential;
    }

    @Override
    public Link getLink() {
        return self;
    }

}
