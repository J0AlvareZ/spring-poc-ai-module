export enum CrudPromptNames {
  "goal" = "goal",
  "controllerType" = "controllerType",
  "entityName" = "entityName",
  "pluralName" = "pluralName",
  "entityId" = "entityId",
  "package" = "package",

  "clientName" = "clientName",
  "baseUrl" = "baseUrl"
}

export const GoalChoices = ["controller", "service", "dao", "repository", "client"];
export const IdTypeChoices = ["Integer", "Long", "UUID", "String"];
//export const ClientTypeChoices = ["WebClient", "Feign", "RestTemplate"];

export type AnswersCrud = { [P in CrudPromptNames]: string }