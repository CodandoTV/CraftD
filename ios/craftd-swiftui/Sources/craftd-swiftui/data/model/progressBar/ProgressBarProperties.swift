struct ProgressBarProperties: Decodable {
    let text: String?
    let alignment: CraftDAlign?
    let textAlign: CraftDAlign?
    let progressColor: String?
    let textColor: String?
    let progress: Double?
    let height: Double?
    let spaceBetween: Double?
}
