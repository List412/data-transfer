package Tables

import org.jetbrains.exposed.sql.Table

object JobsResume : Table() {
    val job = reference("job_id", Tables.Jobs).primaryKey(0)
    val resume = reference("resume_id", Tables.Resumes).primaryKey(1)
}