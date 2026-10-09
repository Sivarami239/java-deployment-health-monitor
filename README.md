# Deployment Health Monitor

Java 17+ sample/training project aligned to a **Software Engineer (Dev Ops)** role.

A Java console tool that evaluates service health from latency, error rate and CPU metrics, then produces a deployment recommendation.

## Run

```powershell
mkdir out
javac -d out src\Main.java
java -cp out Main
```

## Notes

- Uses only the Java standard library.
- Intended as a small skills demonstration/training project.
- No passwords, tokens or other secrets should be committed.
