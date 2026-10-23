# pr-review: payments service

A small Spring Boot service that lets customers pay bills from their accounts.
`main` is the reviewed, merged baseline. Every exercise is an open pull request against `main`.

## How to practise

1. Open the exercise PR. Read the description first: it is the ticket the code must satisfy.
2. Start a 45-minute timer.
3. Review on GitHub like a real reviewer:
   - inline comments on the lines that matter, each with **severity → problem → consequence → fix**
     (severity: Blocker / Should fix / Nit / Question)
   - finish with a review summary: verdict, top blockers, one thing done well
4. Submit the review (Request changes / Comment / Approve).

Order that works: map the files → core logic → supporting classes → tests → summary.

## Run it

```bash
mvn verify            # build + tests
mvn spring-boot:run   # starts on :8080 with an in-memory H2 database
```

```bash
curl -X POST localhost:8080/api/payments -H "Content-Type: application/json" \
  -d '{"accountId":1,"billerCode":"EVN-HCM","amount":150000}'
```

## Stack

Java 21 · Spring Boot 3.3 · Spring Data JPA · H2
