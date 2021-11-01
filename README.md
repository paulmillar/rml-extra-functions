# RML Extra Functions (REF)

This repository contains Java code to provide some simple, additional
functions for manipulating input data.  It also contains a description
of the available functions using the [Functional
Ontology](https://fno.io/) (FnO).

The primary use-case is to extend the abilities of
[RMLMapper](https://github.com/RMLio/rmlmapper-java) to handle input
data.

## How to build

The project uses [Apache Maven](https://maven.apache.org/) to manage
the build process.  Once Maven is installed, building the package is
relatively simple.  The command `mvn package` will create a jar file
in the `target/` directory.

## How to use

Here is a typical invocation of RMLMapper for a mapping that uses
functions defined in this repository:

```shell-session
$ java -jar rmlmapper-4.12.0-r360-all.jar -f functions.ttl -o output.ttl -s turtle -m mapping.ttl
```

In this command, the `-f` argument (`functions.ttl`) is the path to
the FnO description of the REF functions.  This file may be found in
this repository under the path `src/main/resources/functions.ttl`.

The file `functions.ttl` assumes that the jar file is located in the
current working directory when running the RMLMapper command.

The `-m` argument (`mapping.ttl`) is the path to the RML mapping
description.  This should make use of the REF functions as described
in the [RMLMapping
documentation](https://github.com/RMLio/rmlmapper-java#including-functions).

The function definitions use a prefix of
`https://github.com/paulmillar/rml-extra-functions#`; for example, the
function `resolveDirectory` has the ID
`https://github.com/paulmillar/rml-extra-functions#resolveDirectory`.


## Examples

Here are some examples to illustrate how the REF functions may be used
within an RML mapping.

In these examples, the prefix `ref` is used as the prefix for
`https://github.com/paulmillar/rml-extra-functions#`.

Such prefixes are typically defined near the top of the file:

```turtle
@prefix ref:  <https://github.com/paulmillar/rml-extra-functions#> .
```

In the following example, the subject IRI is derived from the item
`id`.  This item is already an IRI, but the desired subject IRI is
obtained by resolving a relative path using the `resolveDirectory`
function; for example, if `id` has value
`https://example.org/11223344` then the desired subject IRI is
`https://example.org/11223344/address-0`.

```turtle
  rr:subjectMap [
    rr:class grid:Address ;

    fnml:functionValue [
      rr:predicateObjectMap [
        rr:predicate fno:executes ;
	rr:object ref:resolveDirectory ] ;
      rr:predicateObjectMap [
        rr:predicate ref:p_string_base_uri ;
	rr:objectMap [ rml:reference "id" ] ] ;
      rr:predicateObjectMap [
        rr:predicate ref:p_string_path ;
	rr:object "address-0" ]
    ] ;
    rr:termType rr:IRI
  ];
```

In the following example, a predicate-object pair is added where the
object is derived from the `id`.  The predicate IRI is a constant
value.  The object IRI is derived as above, by appending `/address-0`
to the `id` value using the `resolveDirectory` function.

```turtle
  rr:predicateObjectMap [
    rr:predicate grid:hasAddress;

    rr:objectMap [
      fnml:functionValue [
        rr:predicateObjectMap [
          rr:predicate fno:executes ;
          rr:object ref:resolveDirectory ] ;
        rr:predicateObjectMap [
          rr:predicate ref:p_string_base_uri ;
          rr:objectMap [ rml:reference "id" ] ] ;
        rr:predicateObjectMap [
          rr:predicate ref:p_string_path ;
          rr:object "address-0" ]
      ]
    ]
  ];
```
