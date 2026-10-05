# Module 0: Setup (the JDK, VS Code, Git & GitHub)

## Hook: a language that pops up a window from one line

As soon as Java is installed (step 1 below), open a terminal, type
`jshell` and press Enter. You'll see a `jshell>` prompt. Java is now
waiting for you. Type these lines, pressing Enter after each:

```java
jshell> "Java! ".repeat(5)
jshell> 365 * 24 * 60 * 60
jshell> javax.swing.JOptionPane.showMessageDialog(null, "Hello from Java!")
```

The first line repeats a piece of text five times. The second works out
how many seconds there are in a year. The third opens a real window on
your screen with a message and an **OK** button. One line of Java just
reached outside the terminal and drew on your desktop. (It may open
*behind* your terminal. Look for it, then click **OK**.)

Type `/exit` and press Enter to leave the `jshell>` prompt.

You don't know what `javax.swing.JOptionPane` means yet. That's fine:
that's what the rest of this course is for. Every module from here
replaces one piece of "I don't know why that worked" with "I know
exactly why." (You'll build whole windowed programs in Module 18.)

## 1. Install the Java Development Kit (JDK)

Java is a **compiled** language. Before your code runs, a program called
the **compiler** (`javac`) translates it into **bytecode**, and then the
**Java Virtual Machine** (`java`) runs that bytecode. Module 1 explains
this properly. For now, you need both tools, and they come together in
the **JDK** (Java Development Kit).

Install a **JDK, version 21 or newer**. We recommend the free **Eclipse
Temurin** build, which works the same on every operating system. Any
LTS ("long-term support") version is a good choice: 21 or 25. Every
program in this course also works on Java 17.

> **JDK, not JRE.** A JRE (Java Runtime Environment) can *run* Java
> programs but can't *compile* them. It has no `javac`. Always install
> the JDK.

**Windows**

1. Go to [adoptium.net](https://adoptium.net), choose the latest **LTS**
   version, **Windows**, **x64**, **JDK**, and download the **`.msi`**
   installer.
2. Run it. On the *Custom Setup* screen, make sure **Add to PATH** and
   **Set JAVA_HOME variable** are both set to *"Will be installed on
   local hard drive"*. `JAVA_HOME` is off by default. Turn it on: some
   tools you'll use later (Maven, in Module 17) need it.
3. Open a **new** terminal (PowerShell or Command Prompt) and check:

   ```bash
   java -version
   javac -version
   ```

**macOS**

1. Go to [adoptium.net](https://adoptium.net) and download the latest
   **LTS** **JDK** `.pkg` installer for macOS (choose **aarch64** for an
   Apple Silicon Mac, M1 or later, and **x64** for an older Intel Mac).
2. Run it, then open a **new** Terminal window and check:

   ```bash
   java -version
   javac -version
   ```

**Linux** (Ubuntu/Debian)

```bash
sudo apt update && sudo apt install openjdk-21-jdk -y
java -version
javac -version
```

You should see something like `openjdk version "21.0.5"` and
`javac 21.0.5`, not "command not found." The two version numbers should
match.

## 2. Install VS Code

Download **Visual Studio Code** from
[code.visualstudio.com](https://code.visualstudio.com) and install it.
Then open the Extensions panel (`Ctrl+Shift+X`, or `Cmd+Shift+X` on a
Mac), search for **Extension Pack for Java**, and install the one **by
Microsoft**. It gives you colour-coded code, errors underlined in red
*as you type* (before you even compile), a **Run** button, and the
debugger you'll use in Module 13.

Check it works:

1. In VS Code, choose **File → Open Folder…** and open any empty folder.
2. Create a new file called `Test.java`. The file name matters in Java:
   it must match the class name inside it, capital letter included.
3. Type this in exactly:

   ```java
   public class Test {
       public static void main(String[] args) {
           System.out.println("VS Code works!");
       }
   }
   ```

4. Click the **▶ Run** button in the top right corner (or the small
   **Run** link that appears just above `main`).

A terminal panel opens at the bottom and shows `VS Code works!`. The
first time, the Java extension may take a minute to start up. You'll
see "Java: Ready" in the status bar at the bottom when it's done.

## 3. Install and configure Git

**Git** keeps a complete history of every change you make to your code,
so you can always go back, and it's how you'll hand in your work.

- **Windows:** install **Git for Windows** from
  [git-scm.com](https://git-scm.com). Accept the default options.
- **macOS:** open Terminal and type `git --version`. If Git isn't
  installed, macOS offers to install it for you. Say yes.
- **Linux:** `sudo apt install git -y`

Then tell Git who you are. This name and email are attached to every
commit you make:

```bash
git config --global user.name "Your Full Name"
git config --global user.email "the-email-you-use-for-github@example.com"
```

## 4. Create a GitHub account

If you don't already have one, sign up at [github.com](https://github.com).
Choose a professional-looking username. This account will hold your work
long after this course ends, and employers do look.

## 5. Get the course materials

The course itself lives in a public GitHub repository. **Clone** it
(download a linked copy) once, to a folder where you keep your code:

```bash
git clone https://github.com/datlogix/Java4Beginners.git
```

This copy is **read-only for you**. Treat it as your textbook. Whenever
your instructor says the course has been updated, get the latest version
with:

```bash
cd Java4Beginners
git pull
```

## 6. Create your own coursework repository

Your own work goes in **your own repository**, not in the course copy.

1. On GitHub, click **+ → New repository**.
2. Name it `java-coursework`, set it to **Private**, and tick **Add a
   README file**. Under **Add .gitignore**, choose **Java**: it stops
   Git from saving the compiled `.class` files Java creates. Click
   **Create repository**.
3. Go to **Settings → Collaborators → Add people** and add your
   instructor's GitHub username (they'll tell you what it is). This is
   how your instructor sees and grades your work. Nothing is emailed or
   uploaded anywhere else.
4. Clone it next to the course folder:

   ```bash
   git clone https://github.com/YOUR-USERNAME/java-coursework.git
   ```

You should now have two folders side by side:

```
Java4Beginners/     <- the course (read it, run it, don't edit it)
java-coursework/    <- your work (edit, commit, push)
```

For every module, create a matching folder in `java-coursework`, such
as `01-first-steps/`, and copy that module's `exercises/` and `project/`
starter files into it before you start.

## 7. Make your first commit

Open your `java-coursework` folder in VS Code (**File → Open
Folder…**). Create a folder called `00-setup` and, inside it, a file
called `Hook.java` containing:

```java
public class Hook {
    public static void main(String[] args) {
        System.out.println("Hello from YOUR NAME");
        System.out.println("I just ran my first Java file.");
        javax.swing.JOptionPane.showMessageDialog(null, "Hello from YOUR NAME!");
    }
}
```

Replace both `YOUR NAME`s with your name (keep the quotation marks),
then run it with the ▶ button, or from the terminal:

```bash
java 00-setup/Hook.java
```

Two lines appear in the terminal, and a window pops up with your name
in it. Click **OK** to close it and end the program.

Now save it to your Git history and send it to GitHub:

```bash
git add 00-setup/Hook.java
git commit -m "Complete Module 0 setup"
git push
```

The first time you push, Git asks you to sign in to GitHub. On Windows a
browser window opens. On macOS/Linux, if it asks for a password, use a
[personal access token](https://github.com/settings/tokens) instead
(your normal GitHub password won't work here).

Refresh your repository page on GitHub. You should see `00-setup/Hook.java`
and your commit message. **This is your workflow for every module from
now on**: edit code locally, `git add` the files you changed,
`git commit` with a short description, then `git push`. Your instructor
only sees what you've pushed.

## Common setup problems

- **`java` or `javac` is "not recognized" or "command not found".**
  On Windows, *Add to PATH* wasn't selected in the installer. Run the
  installer again and choose *Modify*. On any system, close and reopen
  the terminal (and VS Code) after installing.
- **`java -version` works but `javac` doesn't.** You installed a JRE,
  or an old Java that came with another program. Install the JDK from
  adoptium.net as above.
- **`java -version` and `javac -version` show different numbers.** You
  have more than one Java installed, and the older one comes first on
  your PATH. Uninstall the old one, or ask your instructor to help you
  fix the PATH.
- **VS Code says "Java runtime could not be located", or the ▶ button
  never appears.** Make sure you opened a *folder* (not just a single
  file), and that the *Extension Pack for Java* is installed. Then press
  `Ctrl+Shift+P` (`Cmd+Shift+P` on a Mac), type **Java: Configure Java
  Runtime**, and check it found your JDK.
- **`error: class Test is public, should be declared in a file named
  Test.java`.** The file name and the class name don't match. Java is
  fussy about this, capital letters included.
- **`git push` rejects your password.** GitHub doesn't accept account
  passwords from the terminal. Use a personal access token, or install
  the [GitHub CLI](https://cli.github.com) and run `gh auth login` once.
- **Nothing shows up on GitHub after `git push`.** Run `git status`. If
  it says "nothing to commit", you probably forgot `git add` before
  `git commit`.
- **You typed `java Hook.java` at the `jshell>` prompt** (or Java code
  straight into the terminal). The `jshell>` prompt (Java) and your
  terminal prompt (your operating system) are two different places. If
  you see `jshell>`, type `/exit` to get back to the terminal.

## Try it yourself

1. Confirm `java -version`, `javac -version` and `git --version` all
   work.
2. Run the three hook lines at the `jshell>` prompt.
3. Clone the course repo, and create, clone, and share your
   `java-coursework` repo with your instructor.
4. Add `00-setup/Hook.java`, run it, commit, and push.
5. Confirm your commit appears on GitHub.

Next: **[Module 1: First Steps](../01-first-steps/README.md)**.
