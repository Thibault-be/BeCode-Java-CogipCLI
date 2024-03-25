# The COGIP Microservice Architecture Challenge - CLI

This challenge encompasses weeks 6 to 10 from the BeCode Java track training.
The idea is to create a REST API and non-interactive CLI for an imaginary company named Cogip.

This is by no means a production ready piece of software, but was designed to learn and become more familiar with Java development.

## Features

The non-interactive CLI connects to the REST API (see below) to manipulate the data in the database.
You can get, read, update or delete information using a scripting language (explained below) for Users, Companies, Contacts and Invoices.

## Development

The CLI was created using Spring Boot and Spring Shell.

## How to use

1. First login with the login command
   2. The other commands are not available without first logging in

```
login admin admin
login -u admin -p admin
login --username admin --password admin
```

2. After logging in you can work with information in the tables users, companies, invoices, contacts.
   3. Permissions:
      4. ADMIN: read and write access everywhere
      5. ACCOUNTANT: read and write access everywhere. But only read access for table users.
      6. INTERN: read access everywhere. No access for table users


## User commands

Possible flags:
```
--id / -i
--user / -u
--password / -p
--role / -r
```

`user get`  prints out all the users in the table. Or by optional filters:  `--user`, `--role`.

`user post`  adds a new user. Required flags: `--user`, `--password`, `--role`.

`user put` updates a user. Required flags: `--id`. Optional flags: `--user`, `--role`

`user delete` deletes a user. Required flags: `--id`.


## Company commands

Possible flags:
```
--id / -i
--name / -n
--country / -c
--vat / -v
--type / t
```

`company get` 

prints out all the companies in the table. Or by optional filters: `--id`, `--name`, `--country`, `--vat`, `--type`.


`company post` 

adds a new company. Required flags: `--name`, `--country`, `--vat`, `--type`.

`company put`

updates a company. Required flags: `--id`. Optional flags: `--name`, `--country`, `--vat`, `--type`

`company delete`

deletes a company. Required flags: `--id`.


## Contact commands

Possible flags:
```
--id / -i
--firstname / --first / -f
--lastname / --last / -l 
--phone / -p
--email / -e
--companyId / --compId
--companyName / --compName
```

`contact get`  

prints out all the contacts in the table. Or by optional filters: `--id`, `--firstname`, `--lastname`, `--phone`, `--companyName`.

`contact post`  

adds a new contact. Required flags: `--firstname`, `--lastname`. Optional flags: `email`, `--phone`, `--companyName`

`contact put` 

updates a contact. Required flags: `--id`. Optional flags: `--firstname`, `--lastname`, `email`, `--phone`, `--companyId`

`contact delete` 

deletes a contact. Required flags: `--id`.


## Invoice commands

Possible flags:
```
--id / -i
--companyName / company" / --comp
--companyId / --compId
--contactName / --contact --cont
--contactId / --contId
--invoiceNumber / --invoice --inv
--value / --val / -v
--currency / --cur / -c
--invoiceType / --type / -t
--invoiceStatus / --status / -s
```

`invoice get`  
prints out all the invoices in the table. Or by optional filters: `--id`, `--companyName`, `--companyId`, `--contactName`, `--contactId`, `--invoiceNumber`, `--value`, `--currency`, `--invoiceType`, `--invoiceStatus`.

`invoice post`  
adds a new invoice. Required flags: `--companyId`, `contactId`, `invoiceNumber`, `--value`, `--currency`, `invoiceType`, `invoiceStatus`.

`invoice put`  
updates an invoice. Required flags: `--id`. Optional flags: `--companyId`, `contactId`, `invoiceNumber`, `--value`, `--currency`, `invoiceType`, `invoiceStatus`.


Invoices cannot be deleted.



Note:

All `get` commands can be printed in 'human print', which is default or in Json by adding `--json j` or `-j j` as an optional flag.

### Help command

Write `help` on its own or followed by any command for an explanation on how to use the command.


## Error Handling

When a command does not render any results or you've entered a wrong command, you will be guided by an error message about what went wrong.


## REST API

The CLI connects to a REST API which can be found here:

https://github.com/Thibault-be/BeCode-Java-CogipRestApi

## License

[MIT](https://choosealicense.com/licenses/mit/)