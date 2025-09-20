# AuthenticSc

AuthenticSc è un plugin per **Velocity Proxy** che permette allo staff di comunicare tra i server tramite una chat privata, completamente **personalizzabile tramite file di configurazione**.

> ✅ Semplice, veloce, leggero e 100% open source.

---

## ✨ Funzionalità

- Comando `/staffchat` (alias `/sc`)
- Formattazione del messaggio personalizzabile
- Supporto ai **colori Minecraft** (&a, &b, ecc.)
- Config file con placeholder: `{player}`, `{server}`, `{message}`
- Permesso personalizzabile per chi può ricevere i messaggi
- Supporto a **Velocity API** (Java 17+)

---

## 🧪 Comandi

| Comando | Descrizione |
|--------|-------------|
| `/staffchat <messaggio>` | Invia un messaggio nello staff chat |
| `/sc <messaggio>`        | Alias di `/staffchat` |

---

## 🔐 Permessi

| Permesso           | Descrizione |
|-------------------|-------------|
| `authenticsc.use` | Consente di **ricevere** i messaggi dello staffchat |

Puoi cambiare il nome del permesso nel `config.yml`.

---

## ⚙️ Configurazione (`config.yml`)

```yaml
permission: "authenticsc.use"

format: "&7[&bStaffChat&7] &8[{server}] &e{player} &f: {message}"

messages:
  only-players: "&cQuesto comando può essere usato solo da un giocatore."
  usage: "&cUtilizzo: /staffchat <messaggio> oppure /sc <messaggio>"
