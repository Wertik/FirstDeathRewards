# FirstDeathRewards
Run commands and reward player when he dies for the first time.

**Native version**
`1.16`

## Features

Keep inventory and experience.
```yaml
keep-inventory: true
keep-exp: true
```

Deny experience and item drops form player. Might be needed on true to ensure no item dupping.
```yaml
deny-drops: true
```

## Rewards

You can 'reward' the player for his first death...

* **...with commands:**
```yaml
rewards:
  commands:
    - 'say Hello world!'
```
With no prefix, command is ran by the console.\
With `p!` a command is executed by the player.\
And with `op!` the command is executed by the player with operator permissions.
```yaml
rewards:
  commands:
    - 'p! say I died!'
    - 'op! plugins'
```

You can then add prefix `rand!` in front of these to pick a random command.
```yaml
rewards:
  commands:
    - 'rand! p! say I died.'
    - 'rand! say %player% died.'
```
^^ This would either run a say command for the player with content `I died.`, **OR** run a say command by console with `say %player% died.`.

* **...with messages:**\
`inform` sends a message to the player.
```yaml
rewards:
  inform:
    - '&7You have died. You loser.'
```

`broadcast` broadcasts a message to all online players.
```yaml
rewards:
  broadcast:
    - '&f%player% &7died for the first time.'
```

* **...with some common currencies:**\
`money` gives the player Vault economy.\
`tokens` gives the player TokenManager tokens.
```yaml
rewards:
  money: 1000
  tokens: 10
```

* **...with items:**
```yaml
rewards:
  items:
    diamond:
      type: DIAMOND
      amount: 1-3
      name: '&3Little diamond'
      lore:
        - '&7Given to &f%player% &7for his first death.'
      enchants:
        - 'FORTUNE;2'
      flags:
        - 'HIDE_ENCHANTS'
      nbt:
        - 'MY_KEY;MY_FANCY_DATA'
    # You can also define more items.
    a-stick:
      type: STICK
       name: '&6A stick'
```

`type` - Material of the item\
`name` - Display name\
`lore` - Item lore\
`amount` - Item amount, can be dynamic with syntax `<minimum_amount>-<maximum_amount>`\
`enchants` - Enchantments with levels\
`flags` - Item Flags (HIDE_ATTRIBUTES, HIDE_ENCHANTS)\
`glow` - Item glow, adds LUCK enchantment and HIDE_ATTRIBUTES flag to make the item glow\
`nbt` - NBT data

Both lore and name support coler codes and the `%player%` placeholder.
