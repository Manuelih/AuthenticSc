<h1 align="center">AuthenticSc</h1>
<p align="center">
  🔒 Advanced Staff Chat plugin for Velocity Proxy  
  <br>
  <b>Fully customizable · Lightweight · Open Source</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/velocity-API-blue?logo=velocity&style=flat-square">
  <img src="https://img.shields.io/badge/made%20with-Java-orange?logo=java&style=flat-square">
  <img src="https://img.shields.io/badge/license-MIT-green?style=flat-square">
</p>

---

## ✨ Overview

**AuthenticSc** è un plugin per **Velocity Proxy** progettato per offrire una **staff chat centralizzata**, moderna e completamente configurabile.

✅ Ideale per network Velocity che vogliono comunicazioni staff **private**, **colorate** e **personalizzate**  
⚡ Scritto in **Java 17+** usando **Velocity API**

---

## 🛠️ Features

- 💬 Comandi `/staffchat` e `/sc`
- 🎨 Supporto completo a colori Minecraft (`&a`, `&b`, ecc.)
- 🧩 File `config.yml` per personalizzare:
  - Formato messaggio
  - Messaggi di sistema
  - Permesso richiesto
- 📡 Messaggi visibili globalmente a tutto lo staff connesso
- ⚙️ Placeholder supportati:
  - `{server}` → Nome del server
  - `{player}` → Nome del giocatore
  - `{message}` → Messaggio inviato

---

## 🧪 Comandi

| Comando              | Descrizione                             |
|----------------------|------------------------------------------|
| `/staffchat <msg>`   | Invia un messaggio nello staff chat      |
| `/sc <msg>`          | Alias più rapido di `/staffchat`         |

---

## 🔐 Permessi

| Permesso            | Descrizione                                      |
|---------------------|--------------------------------------------------|
| `authenticsc.use`   | Consente di **ricevere** i messaggi dello staff  |

> 🔧 Il permesso è modificabile da `config.yml`

---

## ⚙️ Configurazione (`config.yml`)

```yaml
permission: "authenticsc.use"

format: "&7[&bStaffChat&7] &8[{server}] &e{player} &f: {message}"

messages:
  only-players: "&cQuesto comando può essere usato solo da un giocatore."
  usage: "&cUtilizzo: /staffchat <messaggio> oppure /sc <messaggio>"
