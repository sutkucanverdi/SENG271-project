```mermaid
flowchart TD
    Start([Start])

    CheckCustomer{Customer exists?}
    FailCustomer[Error message: No customer with given ID]

    CheckItem{Valid item ID?}
    CheckStock{Stock > 0?}

    AddToCart[Add item to cart 
Show confirmation]
    FailItem[Invalid item ID]
    FailStock[Error message: Item temporarily unavailable]

    End([End])

    Start --> CheckCustomer
    CheckCustomer -->|No| FailCustomer --> End
    CheckCustomer -->|Yes| CheckItem

    CheckItem -->|No| FailItem --> End
    CheckItem -->|Yes| CheckStock

    CheckStock -->|No| FailStock --> End
    CheckStock -->|Yes| AddToCart --> End
