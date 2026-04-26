# plop-generator-feng

This is a plop generator that replaces the [Framework ENtity Generator (feng)](https://gitlab.infosisglobal.com/Framework/framework-entity-generator) using plop

Plop is an utilitary that allows us to create new CRUDs, using [handlebarsjs](https://handlebarsjs.com/) templates

## Requirements

In order to use plop we need to install the following dependencies:

- [npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm/)
- [nvm (optional)](https://github.com/nvm-sh/nvm)

## Usage

In order to incorporate this templates into your project, you should clone or paste it into a `/provision/generators/plop` folder.

### package.json

Also, in order to be able to run **plop** through **npm**, you should create (or add to, if it already exists) a `package.json` file with the following content:

```json
{
  "engines": {
    "node": ">=14"
  },
  "scripts": {
    "plop": "cross-env TS_NODE_PROJECT='./provision/generators/plop/ts-node.tsconfig.json' plop --plopfile provision/generators/plop/index.ts"
  },
  "dependencies": {},
  "devDependencies": {
    "cross-env": "^7.0.3",
    "handlebars-helpers": "^0.10.0",
    "node-plop": "^0.26.3",
    "plop": "^2.7.6",
    "prettier": "^2.3.2",
    "prettier-eslint": "^12.0.0",
    "slugify": "^1.6.6",
    "ts-node": "^10.0.0",
    "typescript": "^4.3.5"
  }
}
```

Once the `package.json` is created execute an `npm install`, and after it installs all necessary dependencies, you can follow this do for actual usage:

- https://infosistech.atlassian.net/wiki/x/BICAHg