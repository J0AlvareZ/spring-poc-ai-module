import { Actions, PlopGeneratorConfig } from "node-plop"
import * as path from "path"
import { baseProjectPath, templatesPath } from "../utils"
import {
  AnswersCrud,
  CrudPromptNames,
  GoalChoices,
  IdTypeChoices
} from "./entities"

const helpers = require("handlebars-helpers")(["string"])

export const crudGenerator: PlopGeneratorConfig = {
  description: "Create a CRUD for a named entity",
  prompts: [
    {
      type: "list",
      name: CrudPromptNames.goal,
      message: "Select a goal",
      choices: GoalChoices
    },
    {
      type: "input",
      name: CrudPromptNames.entityName,
      message: "What is the entity's name?",
      default: "sample",
      when: (answers: AnswersCrud) => answers.goal !== "client"
    },
    {
      type: "input",
      name: CrudPromptNames.pluralName,
      message: "What is the entity's plural name? (the db's table name)",
      default: (ans: AnswersCrud) => ans.entityName.concat("s").toLowerCase(),
      when: (answers: AnswersCrud) => answers.goal !== "client"
    },
    {
      type: "list",
      name: CrudPromptNames.entityId,
      message: "What is the entity's id type?",
      choices: IdTypeChoices,
      when: (answers: AnswersCrud) => answers.goal !== "client"
    },
    {
      type: "input",
      name: CrudPromptNames.package,
      message: "Which is the base package for the future files?",
      default: "com.joalvarez.springpocaimodule"
    },

    {
      type: "input",
      name: CrudPromptNames.clientName,
      message: "What is the client name?",
      default: "sample",
      when: (answers: AnswersCrud) => answers.goal === "client"
    },
    {
      type: "input",
      name: CrudPromptNames.baseUrl,
      message: "Enter the base API Url: ",
      default: "https://api.example.com",
      when: (answers: AnswersCrud) => answers.goal === "client",
      validate: (input) => input.startsWith("http")
        ? true
        : "URL must start with http:// or https://"
    }
  ],
  actions: (data) => {
    const answers = data as AnswersCrud

    const projectRootPath = path.join(baseProjectPath, answers.package.replace(/\./g, "/"))

    const actions: Actions = []

    let goals: Map<string, string> = new Map()

    switch (answers.goal) {
      case "controller":
        goals.set("Controller", "controller")
      case "service":
        goals.set("Service", "service")
      case "dao":
        goals.set("DAO", "data/dao")
    }

    if (answers.goal === "client") {
        goals.set("Client", "client")
    }

    if (answers.goal !== "client") {
      goals.set("Repository", "data/repository")
      goals.set("Mapper", "data/mapper")
      goals.set("", "data/domain")
      goals.set("DTO", "data/dto")
    }

    goals.forEach((goalPath, goal) => {
      const titleizedName = helpers.titleize(answers.entityName).replace(" ", "")
      const className = goal === "Client"
        ? `${titleizedName}${answers.clientName}Client`
        : titleizedName.concat(goal)
      actions.push({
        type: "add",
        templateFile: `${templatesPath}/${goal.length == 0 ? "Domain" : goal}.hbs`,
        path: path.join(projectRootPath, goalPath, className.concat(".java")),
        abortOnFail: false,
        data: {
          className: className,
          name: titleizedName
        }
      })
    })

    return actions
  }
}