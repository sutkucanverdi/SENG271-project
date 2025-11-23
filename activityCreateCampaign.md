```mermaid
flowchart TD
    Start([Start])

    CheckAdmin{Admin exists?}
    CheckRate{Rate ≤ 50%?}
    CheckExists{Campaign already exists?}

    Create[Create campaign]
    FailAdmin[Admin not found]
    FailRate[Rate too high]
    FailExists[Campaign exists]

    End([End])

    Start --> CheckAdmin
    CheckAdmin -->|No| FailAdmin --> End
    CheckAdmin -->|Yes| CheckRate

    CheckRate -->|No| FailRate --> End
    CheckRate -->|Yes| CheckExists

    CheckExists -->|Yes| FailExists --> End
    CheckExists -->|No| Create --> End
