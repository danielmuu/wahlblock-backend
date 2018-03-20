package de.devdanmu.wahlblockbackend.controller;

import de.devdanmu.wahlblockbackend.jpa.Election;
import de.devdanmu.wahlblockbackend.jpa.LoginVoter;
import de.devdanmu.wahlblockbackend.jpa.VoterHash;
import de.devdanmu.wahlblockbackend.service.ElectionInfoService;
import de.devdanmu.wahlblockbackend.service.VoterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danmu
 * created on 2018-03-10
 */
@RestController
public class WahlblockController {

    private final VoterLoginService voterLoginService;
    private final ElectionInfoService electionInfoService;

    @Autowired
    public WahlblockController(final VoterLoginService voterLoginService, final ElectionInfoService electionInfoService) {
        this.voterLoginService = voterLoginService;
        this.electionInfoService = electionInfoService;
    }

    @PostMapping("/api/v1/voter/login")
    public VoterHash setLoginAndGetPermission(@RequestBody LoginVoter loginVoter) throws Exception {
        return voterLoginService.startVoterLogin(loginVoter);
    }

    @GetMapping("/api/v1/election")
    public Election getElectionInfo() throws Exception {
        return electionInfoService.getElection();

    }
}
