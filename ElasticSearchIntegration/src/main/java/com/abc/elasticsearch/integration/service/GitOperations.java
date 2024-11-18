package com.abc.elasticsearch.integration.service;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.URIish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Component
public class GitOperations {
	private final Logger log = LoggerFactory.getLogger(GitOperations.class);
	public String getRepoURL() throws IOException, URISyntaxException {
		File repoDir = new File("");
		Repository repository = new FileRepositoryBuilder()
				.setGitDir(repoDir)
				.build();
		List<RemoteConfig> remoteConfigs = RemoteConfig.getAllRemoteConfigs(repository.getConfig());
		String repoURL="https://gitlab.example.com/api/v4/projects";
		for(RemoteConfig remote: remoteConfigs){
			List<URIish> urIishList =remote.getURIs();
			for(URIish uri: urIishList){
				log.info("URL: "+uri.toString());
			}

		}
		return repoURL;
	}
}
