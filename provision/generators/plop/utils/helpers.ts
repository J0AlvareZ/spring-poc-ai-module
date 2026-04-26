import * as path from "path"
import fs from "fs"

export const baseProjectPath = path.join(__dirname, "../../../../src/main/java/")
export const templatesPath = path.join(__dirname, "../templates")

export function hyphenate(data: string) {
  return data
    .replace(/([a-zA-Z])(?=[A-Z])/g, "$1-")
    .replace(":", "-")
    .toLowerCase()
}

export function pathExists(path: string) {
  return fs.existsSync(path)
}

export function pathMake(path: string) {
  return fs.mkdirSync(path)
}
